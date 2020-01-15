package com.teoan.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 　 @author Teoan
 *  文章标签关联
 * @date 2020/1/15 16:14
 */

@Data
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -5816783232020910492L;
    private Integer articleId;
    private Integer tagId;

    public ArticleTagRef(){}

    public ArticleTagRef(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
