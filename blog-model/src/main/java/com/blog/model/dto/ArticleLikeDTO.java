package com.blog.model.dto;

import lombok.Data;

@Data
public class ArticleLikeDTO {
    private Integer articleId;
    private Integer userId;
    private Boolean operateType;
}
