package com.web.core.service.noauth;


import com.web.core.client.AuthClient;
import com.web.core.exception.RecordException;
import com.web.core.service.auth.ManagerDto;
import com.web.core.service.auth.ManagerService;
import com.web.core.service.auth.PasswordDto;
import com.web.core.service.auth.PasswordService;
import com.web.core.service.authClient.AuthClientService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;
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
    private final AuthClientService authClient;

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
    @Transactional
    public Long handlePasswordEncryption(String password, String userId) throws BackendException {
        String encryptedPassword = authClient.encodePassword(password);

        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setLastChangedTime(LocalDateTime.now().toString());
        passwordDto.setExpTime(LocalDateTime.now().plusDays(90).toString());
        passwordDto.setPassword(encryptedPassword);

        PasswordDto savedPassword = passwordService.save(passwordDto, userId);

        return savedPassword.getId();
    }



    // 패스워드 암호화 및 저장, 암호화된 패스워드의 ID 반환
    @Transactional
    public String hana(String password, String userId) throws BackendException {
        return authClient.encodePassword(password);
    }
}
