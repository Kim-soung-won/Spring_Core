package com.web.core.service.auth;

import com.web.core.client.AuthClient;
import com.web.core.repository.rdb.auth.Manager;
import org.base.base.exception.BackendException;
import org.base.base.repository.rdb.CrdRepository;
import org.base.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Qualifier("managerService")
public class ManagerService extends CrudService<Manager, ManagerDto, String> {

    private final PasswordService passwordService;
    private final AuthClient authClient;

    @Autowired
    public ManagerService(CrdRepository<Manager, String> repository,
                          PasswordService passwordService, AuthClient authClient) {
        super(repository);
        this.passwordService = passwordService;
        this.authClient = authClient;
    }

    @Override
    @Autowired
    @Qualifier("managerRepository")
    public void setRepository(CrdRepository repository) { this.repository = repository; }

    @Override
    public Class<?> getDtoClazz() { return ManagerDto.class; }


    @Transactional(readOnly = true)
    public ManagerDto getManager(String managerId){
        Optional<Manager> managerOptional = this.repository.findById(managerId);
        if(!managerOptional.isPresent()){
            return null;
        }
        Manager manager = managerOptional.get();
        ManagerDto managerDto = new ManagerDto(manager);
        managerDto.setId(manager.getId());
        managerDto.setUsername(manager.getUsername());
        managerDto.setPwdId(manager.getPwdId());
        managerDto.setPassword(manager.getPassword().getPassword());
        managerDto.setEnabled(manager.getEnabled() == 1);

        return managerDto;
    }
}
