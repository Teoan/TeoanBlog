package com.teoan.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 　 @author Teoan
 *
 * @date 2020/1/15 16:50
 */

@Data
public class Page implements Serializable {
    private static final long serialVersionUID = 3927496662110298634L;
    private Integer pageId;

    private String pageKey;

    private String pageTitle;

    private String pageContent;

    private Date pageCreateTime;

    private Date pageUpdateTime;

    private Integer pageViewCount;

    private Integer pageCommentCount;

    private Integer pageStatus;
}
