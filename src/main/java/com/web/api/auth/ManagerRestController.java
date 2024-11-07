package com.web.api.auth;

import com.web.repository.rdb.auth.Manager;
import com.web.service.auth.ManagerDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.base.base.api.CrudRestController;
import org.base.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
@Tag(name = "관리자", description = "관리자 관리")
public class ManagerRestController extends CrudRestController<Manager, ManagerDto, String> {
    @Override
    @Autowired
    @Qualifier("managerService")
    protected void setService(CrudService<Manager, ManagerDto, String> service) {
        this.name = "관리자";
        this.service = service;
    }
}
