package com.blog.service;

import com.blog.model.dto.ArticleRankListDTO;

import java.util.List;
import java.util.Map;

public interface ArticleLikeService {
    //添加点赞
    Boolean addLike(int articleId,String userId);

    //取消点赞
    Boolean removeLike(int articleId,String userId);

    //查询用户是否点赞
    Boolean isLiked(int articleId,String userId);

    //查询文章点赞数
    Integer getLikeCount(int articleId);

    //获取点赞前10的文章
    List<ArticleRankListDTO> getTopLikedArticles();


}
