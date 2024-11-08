package com.web.core.service.noauth;


import com.web.core.client.AuthClient;
import com.web.core.service.auth.ManagerDto;
import com.web.core.service.auth.ManagerService;
import com.web.core.service.auth.PasswordDto;
import com.web.core.service.auth.PasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.base.base.exception.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoAuthService {

    private final ManagerService managerService;
    private final PasswordService passwordService;

    @Autowired
    @Qualifier("AuthHttpClient")
    public void setClient(AuthClient AuthClient) {
        this.authClient = AuthClient;
    }

    protected AuthClient authClient;

    @Transactional
    public String signup(ManagerDto managerDto) throws BackendException {
        try{
            if(managerService.get(managerDto.getId()) != null){
                return "이미 존재하는 아이디입니다.";
            }
            managerDto.setPwdId(handlePasswordEncryption(managerDto.getPassword(), managerDto.getId()));
            managerDto.setEnabled(true);
            managerService.save(managerDto, managerDto.getId());
            return managerDto.getId();
        } catch (BackendException e){
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    // 패스워드 암호화 및 저장, 암호화된 패스워드의 ID 반환
    public Long handlePasswordEncryption(String password, String userId) throws BackendException {
        String encryptedpassword = authClient.encodePassword(password).getData();

        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setLastChangedTime(LocalDateTime.now().toString());
        passwordDto.setExpTime(LocalDateTime.now().plusDays(90).toString());
        passwordDto.setPassword(encryptedpassword);

        PasswordDto savedPassword = passwordService.save(passwordDto, userId);

        return savedPassword.getId();
    }
}
