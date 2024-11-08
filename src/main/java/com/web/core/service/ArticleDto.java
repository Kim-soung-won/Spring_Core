package com.web.core.service;

import com.web.core.repository.rdb.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.base.base.service.CrudDto;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ArticleDto extends CrudDto {

    private Long id;
    private String title;
    private String content;
    private boolean activeYn;

    public ArticleDto(Article entity) {
        super(entity);
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.activeYn = entity.getActiveYn() == 1;
    }

    @Override
    public Article toEntity() {
        return Article.builder()
                .id(id)
                .title(this.title)
                .content(this.content)
                .createdWho(this.createdWho)
                .updatedWho(this.updatedWho)
                .activeYn(this.activeYn ? 1 : 0)
                .build();
    }
}
