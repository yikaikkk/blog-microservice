package com.blog.service;

import com.blog.model.dto.ArticleRankListDTO;

import java.util.List;

public interface RankingListService {
    
    /**
     * 列出文章排行榜
     * @return 文章排行榜
     */
    List<ArticleRankListDTO> listArticlesTop();

}
