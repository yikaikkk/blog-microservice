package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.entity.Article;
import com.blog.mapper.ArticleMapper;
import com.blog.model.dto.ArticleRankListDTO;
import com.blog.service.RankingListService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.blog.service.RedisService;
import org.apache.dubbo.config.annotation.DubboService;

import static com.blog.constant.RedisConstant.ARTICLE_VIEWS_COUNT;

@DubboService(version = "1.0.0")
public class RankingListServiceImpl implements RankingListService {
    //构造器注入
    private final RedisService redisService;
    private final ArticleMapper articleMapper;
    //构造器注入
    public RankingListServiceImpl(RedisService redisService, ArticleMapper articleMapper) {
        this.redisService = redisService;
        this.articleMapper = articleMapper;
    }


    @Override
    public List<ArticleRankListDTO> listArticlesTop() {
        Map<Object, Double> articleMap = redisService.zReverseRangeWithScore(ARTICLE_VIEWS_COUNT, 0, 10);
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
       //按照viewsCount排序
       articleRankDTOList.sort(Comparator.comparing(ArticleRankListDTO::getViewsCount).reversed()); 
       //删除articleTitle为空的
       articleRankDTOList.removeIf(articleRankListDTO -> articleRankListDTO.getArticleTitle() == null);
    //    articleRankDTOList.removeIf(articleRankListDTO -> !articleTitleMap.containsKey(articleRankListDTO.getArticleId()));
        return articleRankDTOList;
    }

}
