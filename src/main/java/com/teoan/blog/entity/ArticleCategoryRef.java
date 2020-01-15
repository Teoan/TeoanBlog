package com.teoan.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Teoan
 * 文章分类关联
 * @date 2020/1/15 16:05
 * @see
 */
@Data
public class ArticleCategoryRef implements Serializable {

    private static final long serialVersionUID = -6809206515467725995L;

    private Integer articleId;

    private Integer categoryId;

    public ArticleCategoryRef() {
    }

    public ArticleCategoryRef(Integer articleId, Integer categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }
}
