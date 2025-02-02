package com.teoan.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Teoan
 *
 * @date 2020/1/15 15:58
 * @see
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -4415517704211731385L;
    private Integer userId;

    private String userName;

    private String userPass;

    private String userNickname;

    private String userEmail;

    private String userUrl;

    private String userAvatar;

    private String userLastLoginIp;

    private Date userRegisterTime;

    private Date userLastLoginTime;

    private Integer userStatus;

//    文章数量 非数据库字段
    private Integer articleCount;
}
