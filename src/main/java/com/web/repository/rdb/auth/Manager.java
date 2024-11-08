package com.web.repository.rdb.auth;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.base.base.repository.rdb.CrudEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Manager extends CrudEntity {
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "password_id", nullable = false)
    private Long pwdId;

    @OneToOne(optional = false, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY) // 우선 필수
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "password_id", referencedColumnName = "password_id", insertable = false, updatable = false)
    private Password password;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @OneToOne(optional = false, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY) // 우선 필수
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
    private Role role;

    @Column(name = "enabled") //default 1
    private Integer enabled;

    @Builder
    public Manager(String id, String username, String email,
                   Long pwdId, Long roleId, Integer enabled,
                   String createdWho, String updatedWho) {
        super(createdWho, updatedWho);
        this.id = id;
        this.username = username;
        this.email = email;
        this.pwdId = pwdId;
        this.roleId = roleId;
        this.enabled = enabled;
    }

    @PrePersist
    public void prePersist() {
        this.enabled = 1;
    }
}
