package com.web.core.repository.rdb.auth;

import org.base.base.repository.rdb.CrdRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("managerRepository")
public interface ManagerRepository extends CrdRepository<Manager, String> {
}
