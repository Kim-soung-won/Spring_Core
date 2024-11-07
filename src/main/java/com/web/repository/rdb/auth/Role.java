package com.web.repository.rdb.auth;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.base.base.repository.rdb.CrudEntity;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Role extends CrudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", unique = true)
    private String name;

    @Column(name = "rmk") // 주석
    private String rmk;

    @Builder
    public Role(Long id, String name, String rmk, String createdWho, String updatedWho) {
        super(createdWho, updatedWho);
        this.id = id;
        this.name = name;
        this.rmk = rmk;
    }
}
