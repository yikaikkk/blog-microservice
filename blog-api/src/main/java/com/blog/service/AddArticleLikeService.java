package com.blog.service;

import com.blog.model.dto.ArticleRankListDTO;

import java.util.List;

public interface AddArticleLikeService {
    //添加点赞
    Boolean addLike(int articleId,Integer userId);

    //取消点赞
    Boolean removeLike(int articleId,Integer userId);

    //查询用户是否点赞
    Boolean isLiked(int articleId,Integer userId);

    //查询文章点赞数
    Integer getLikeCount(int articleId);

    //获取点赞前10的文章
    List<ArticleRankListDTO> getTopLikedArticles();


}
