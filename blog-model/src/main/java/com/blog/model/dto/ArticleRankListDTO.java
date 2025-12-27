package com.blog.model.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRankListDTO {
    private Integer articleId;
    private Integer viewsCount;
    private String articleTitle;
}
