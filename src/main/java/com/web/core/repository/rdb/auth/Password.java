package com.web.core.repository.rdb.auth;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.base.base.repository.rdb.CrudEntity;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Password extends CrudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "last_changed_time")
    private LocalDateTime lastChangedTime;

    @Column(name = "password_expired_time")
    private LocalDateTime expTime;

    @Builder
    public Password(Long id, String password, LocalDateTime lastChangedTime, LocalDateTime expTime, String createdWho, String updatedWho) {
        super(createdWho, updatedWho);
        this.id = id;
        this.password = password;
        this.lastChangedTime = lastChangedTime;
        this.expTime = expTime;
    }

    @PrePersist
    public void prePersist() {
        this.lastChangedTime = LocalDateTime.now();
        this.expTime = LocalDateTime.now().plusDays(90);
    }
}
