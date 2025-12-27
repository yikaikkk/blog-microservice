package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.constant.RabbitMQConstant;
import com.blog.entity.Article;
import com.blog.mapper.ArticleMapper;
import com.blog.model.dto.ArticleLikeDTO;
import com.blog.model.dto.ArticleRankListDTO;
import com.blog.service.ArticleLikeService;
import com.blog.service.RedisService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.blog.constant.RedisConstant.*;

@DubboService(version = "1.0.0")
public class ArticleLikeServiceImpl implements ArticleLikeService {
    //构造器注入
    private final RedisService redisService;
    private final ArticleMapper articleMapper; 
     private final RabbitTemplate rabbitTemplate; 
    //构造器注入
    public ArticleLikeServiceImpl(RedisService redisService, ArticleMapper articleMapper, RabbitTemplate rabbitTemplate) {
        this.redisService = redisService;
        this.articleMapper = articleMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Boolean addLike(int articleId,String userId) {
        //使用lua脚本实现
        String luaScript = "if redis.call('sismember', KEYS[1], ARGV[1]) == 1 then return 0 end " +
        "redis.call('zincrby', KEYS[2], 1, ARGV[2]) " +
        "redis.call('sadd', KEYS[1], ARGV[1]) " +
        "return 1";

        List<String> keys = new ArrayList<>();
        List<String> args = new ArrayList<>();
        keys.add(ARTICLE_LIKE_USERS + articleId);
        keys.add(ARTICLE_LIKE_COUNT);
        args.add(userId);
        args.add(String.valueOf(articleId));

        Long result = redisService.executeLuaScript(luaScript, keys, args); 

        if (result == 0) {
            return false;
        }
        //往消息队列发送点赞消息
        ArticleLikeDTO articleLikeDTO = new ArticleLikeDTO();
        articleLikeDTO.setArticleId(articleId);
        articleLikeDTO.setUserId(userId);
        articleLikeDTO.setOperateType(true);
        rabbitTemplate.convertAndSend(RabbitMQConstant.LIKE_EXCHANGE, "", articleLikeDTO);
        
        return true;
    }

    @Override
    public Boolean removeLike(int articleId,String userId) {
        //使用lua脚本实现
        String luaScript = "if redis.call('sismember', KEYS[1], ARGV[1]) == 0 then return 0 end " +
        "redis.call('zincrby', KEYS[2], -1, ARGV[2]) " +
        "redis.call('srem', KEYS[1], ARGV[1]) " +
        "return 1";

        List<String> keys = new ArrayList<>();
        List<String> args = new ArrayList<>();
        keys.add(ARTICLE_LIKE_USERS + articleId);
        keys.add(ARTICLE_LIKE_COUNT);
        args.add(userId);
        args.add(String.valueOf(articleId));

        Long result = redisService.executeLuaScript(luaScript, keys, args); 

        if (result == 0) {
            return false;
        }
        //往消息队列发送取消点赞消息
        ArticleLikeDTO articleLikeDTO = new ArticleLikeDTO();
        articleLikeDTO.setArticleId(articleId);
        articleLikeDTO.setUserId(userId);
        articleLikeDTO.setOperateType(false);
        rabbitTemplate.convertAndSend(RabbitMQConstant.LIKE_EXCHANGE, "", articleLikeDTO);
        
        return true;
    }

    @Override
    public Boolean isLiked(int articleId,String userId) {
        return redisService.sIsMember(ARTICLE_LIKE_USERS + articleId, userId);
    }

    @Override
    public Integer getLikeCount(int articleId) {
        return redisService.zScore(ARTICLE_LIKE_COUNT, articleId).intValue();
    }

    @Override
    public List<ArticleRankListDTO> getTopLikedArticles() {
        Map<Object, Double> articleMap = redisService.zReverseRangeWithScore(ARTICLE_LIKE_COUNT, 0, 10);
        //以value为key，value为viewsCount，排序后返回articleRankDTOList，只要前10个
        articleMap = articleMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<ArticleRankListDTO> articleRankDTOList = new ArrayList<>();
        List<Article> articles = articleMapper.selectList(new LambdaQueryWrapper<Article>()
                .select(Article::getId, Article::getArticleTitle)
                .in(Article::getId, articleMap.keySet()));
        Map<Integer, String> articleTitleMap = articles.stream()
                .collect(Collectors.toMap(Article::getId, Article::getArticleTitle));
        for (Map.Entry<Object, Double> entry : articleMap.entrySet()) {
            articleRankDTOList.add(ArticleRankListDTO.builder()
                    .articleId((Integer) entry.getKey())
                    .viewsCount(entry.getValue().intValue())
                    .articleTitle(articleTitleMap.get((Integer) entry.getKey()))
                    .build());
        }
        return articleRankDTOList;
    }
    
}