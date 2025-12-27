package com.blog.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRankListDTO implements Serializable {
    private Integer articleId;
    private Integer viewsCount;
    private String articleTitle;
}
