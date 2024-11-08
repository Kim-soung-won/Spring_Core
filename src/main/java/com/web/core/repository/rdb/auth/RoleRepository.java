package com.web.core.repository.rdb.auth;

import org.base.base.repository.rdb.CrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Long> {
}
