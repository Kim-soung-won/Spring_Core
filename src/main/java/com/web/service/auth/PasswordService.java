package com.web.service.auth;

import com.web.repository.rdb.auth.Password;
import lombok.extern.slf4j.Slf4j;
import org.base.base.repository.rdb.CrdRepository;
import org.base.base.repository.rdb.CrudRepository;
import org.base.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PasswordService extends CrudService<Password, PasswordDto, Long> {

    public PasswordService(CrudRepository<Password, Long> repository) { super(repository); }

    @Override
    @Autowired
    @Qualifier("passwordRepository")
    public void setRepository(CrdRepository repository) { this.repository = repository; }

    @Override
    public Class<?> getDtoClazz() { return PasswordDto.class; }

}
