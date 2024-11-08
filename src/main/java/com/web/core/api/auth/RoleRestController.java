package com.web.core.api.auth;

import com.web.core.repository.rdb.auth.Role;
import com.web.core.service.auth.RoleDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.base.base.api.CrudRestController;
import org.base.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@Tag(name = "Role Controller", description = "권한 컨트롤러")
public class RoleRestController extends CrudRestController<Role, RoleDto, Long> {
    @Override
    @Autowired
    @Qualifier("roleService")
    protected void setService(CrudService<Role, RoleDto, Long> service) {
        this.name = "권한";
        this.service = service;
    }
}
