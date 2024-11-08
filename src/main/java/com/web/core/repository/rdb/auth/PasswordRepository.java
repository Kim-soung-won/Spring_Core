package com.web.core.repository.rdb.auth;

import org.base.base.repository.rdb.CrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("passwordRepository")
public interface PasswordRepository extends CrudRepository<Password, Long> {
}
