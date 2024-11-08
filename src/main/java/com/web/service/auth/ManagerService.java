package com.web.service.auth;

import com.web.client.AuthClient;
import com.web.repository.rdb.auth.Manager;
import org.base.base.exception.BackendException;
import org.base.base.repository.rdb.CrdRepository;
import org.base.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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


}
