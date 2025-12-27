package com.blog.service.impl;

import com.blog.entity.ArticleTag;
import com.blog.mapper.ArticleTagMapper;
import com.blog.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
