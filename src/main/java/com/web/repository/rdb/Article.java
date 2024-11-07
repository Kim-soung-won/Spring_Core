package com.web.repository.rdb;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.base.base.repository.rdb.CrudEntity;

@Entity(name = "article")
@Getter
@Table(name = "article")
@NoArgsConstructor
public class Article extends CrudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "article_title")
    private String title;

    @Column(name = "article_content")
    private String content;

    @Column(name = "article_active")
    private Integer activeYn;

    @Builder
    public Article(Long id, String title, String content, String createdWho, String updatedWho, Integer activeYn) {
        super(createdWho, updatedWho);
        this.id = id;
        this.title = title;
        this.content = content;
        this.activeYn = activeYn;
    }

    @PrePersist
    public void prePersist() {
        this.activeYn = 1;
    }
}
