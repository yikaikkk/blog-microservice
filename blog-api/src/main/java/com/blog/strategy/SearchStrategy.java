package com.blog.strategy;

import com.blog.model.dto.ArticleSearchDTO;

import java.util.List;

public interface SearchStrategy {

    List<ArticleSearchDTO> searchArticle(String keywords);

}
