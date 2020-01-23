package com.teoan.blog.enums;

import lombok.Data;

/**
 * @author Teoan
 * @description
 * @date 2020/1/23 12:03
 */
public enum ArticleCommentStatus {

    ALLOW(1,"允许"),
    NOT_ALLOW(0,"不允许");

    private Integer value;
    private String message;

    ArticleCommentStatus(Integer value,String message){
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
