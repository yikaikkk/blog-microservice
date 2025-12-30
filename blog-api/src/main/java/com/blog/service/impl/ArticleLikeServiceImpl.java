package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.ArticleLikeMapper;
import com.blog.entity.ArticleLike;
import com.blog.service.ArticleLikeService;
import org.springframework.stereotype.Service;

/**
 * 文章点赞服务实现类
 *
 * @author ikkk
 * @since 
 */
@Service
public class ArticleLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements ArticleLikeService {
}
