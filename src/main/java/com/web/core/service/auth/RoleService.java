package com.web.core.service.auth;

import com.web.core.repository.rdb.auth.Role;
import lombok.extern.slf4j.Slf4j;
import org.base.base.repository.rdb.CrdRepository;
import org.base.base.service.CrudService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Qualifier("roleService")
@Service
public class RoleService extends CrudService<Role, RoleDto, Long> {
    public RoleService(CrdRepository<Role, Long> repository) {
        super(repository);
    }

    @Override
    @Qualifier("roleRepository")
    public void setRepository(CrdRepository crdRepository) {
        this.repository = crdRepository;
    }

    @Override
    public Class<?> getDtoClazz() {
        return RoleDto.class;
    }
}
