package com.web.repository.rdb;

import org.base.base.repository.rdb.CrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("articleRepository")
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
