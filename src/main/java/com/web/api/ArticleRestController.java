package com.web.api;


import com.web.repository.rdb.Article;
import com.web.service.ArticleDto;
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
public class ArticleRestController extends CrudRestController<Article, ArticleDto, Long> {
    @Override
    @Autowired
    @Qualifier("articleService")
    protected void setService(CrudService service) {
        this.name = "게시글";
        this.service = service;
    }
}
