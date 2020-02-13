package com.teoan.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/13 17:11
 */
@Data
public class ArticleParam {

    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleOrder;

    private Integer articleStatus;

    private List<Integer> articleTagIds;
}
