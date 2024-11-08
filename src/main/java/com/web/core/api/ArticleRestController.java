package com.web.core.api;


import com.web.core.repository.rdb.Article;
import com.web.core.service.ArticleDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.base.base.api.CrudRestController;
import org.base.base.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/article")
@RestController
@RequiredArgsConstructor
@Tag(name = "게시글", description = "게시글 관리")
public class ArticleRestController extends CrudRestController<Article, ArticleDto, Long> {
    @Override
    @Autowired
    @Qualifier("articleService")
    protected void setService(CrudService service) {
        this.name = "게시글";
        this.service = service;
    }
}
