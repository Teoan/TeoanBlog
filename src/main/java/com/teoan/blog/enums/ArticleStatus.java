package com.teoan.blog.enums;

/**
 * @author Teoan
 * @description
 * @date 2020/2/1 15:56
 */
public enum ArticleStatus {

    PUBLISH(1, "已发布"),
    DRAFT(0, "草稿");

    private int value;

    private String message;

    ArticleStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
