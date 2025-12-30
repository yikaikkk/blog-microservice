package com.blog.consumer;

import com.blog.service.AddArticleLikeService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.blog.constant.RabbitMQConstant.LIKE_QUEUE;

import com.blog.model.dto.ArticleLikeDTO;
import com.blog.service.ArticleLikeService;
import com.alibaba.fastjson.JSON;

import com.blog.entity.ArticleLike;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;



@Component
@RabbitListener(queues = LIKE_QUEUE)
public class ArticleLikeConsumer {

    private final ArticleLikeService articleLikeService;

    public ArticleLikeConsumer(ArticleLikeService articleLikeService) {
        this.articleLikeService = articleLikeService;
    }

    @RabbitHandler
    public void process(byte[] message) {
        //将数据写入到数据库
        ArticleLikeDTO articleLikeDTO = JSON.parseObject(message, ArticleLikeDTO.class);
       //如果是点赞操作，就添加点赞记录
        if(articleLikeDTO.getOperateType()==true) {
           ArticleLike articleLike = new ArticleLike();
           articleLike.setArticleId(articleLikeDTO.getArticleId());
           articleLike.setUserId(articleLikeDTO.getUserId());
           articleLikeService.saveOrUpdate(articleLike);
       }
       //如果是取消点赞操作，就删除点赞记录
       else {
            LambdaQueryWrapper<ArticleLike> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ArticleLike::getArticleId, articleLikeDTO.getArticleId());
            queryWrapper.eq(ArticleLike::getUserId, articleLikeDTO.getUserId());
            articleLikeService.remove(queryWrapper);
       }
    }
}
