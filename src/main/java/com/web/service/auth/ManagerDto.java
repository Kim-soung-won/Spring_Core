package com.web.service.auth;

import com.web.repository.rdb.auth.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.base.base.service.CrudDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ManagerDto extends CrudDto {

    private String id;
    private String username;
    private String password;
    private String email;
    private Long pwdId;
    private Long roleId;


    public ManagerDto(Manager entity) {
        super(entity);
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.pwdId = entity.getPwdId();
        this.password = entity.getPassword().getPassword();
        this.roleId = entity.getRoleId();
    }


    @Override
    public Manager toEntity() {
        return Manager.builder()
                .id(id)
                .username(username)
                .email(email)
                .pwdId(pwdId)
                .roleId(roleId)
                .build();
    }
}
