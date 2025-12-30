package com.blog.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@TableName("t_article_like")        
public class ArticleLike {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer articleId;

    private Integer userId;

}
