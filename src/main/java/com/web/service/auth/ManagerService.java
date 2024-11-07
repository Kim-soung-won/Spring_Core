package com.web.service.auth;

import com.web.repository.rdb.auth.Manager;
import org.base.base.repository.rdb.CrdRepository;
import org.base.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("managerService")
public class ManagerService extends CrudService<Manager, ManagerDto, String> {

    public ManagerService(CrdRepository<Manager, String> repository) { super(repository); }

    @Override
    @Autowired
    @Qualifier("managerRepository")
    public void setRepository(CrdRepository repository) { this.repository = repository; }

    @Override
    public Class<?> getDtoClazz() { return ManagerDto.class; }
}
