package com.teoan.blog.service;

import com.teoan.blog.entity.User;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/2 19:24
 */
public interface UserService {


    /**
     * @Description:  获取用户列表
     * @Param:
     * @return:  用户列表
     **/
    List<User> listUser();


    /**
     * @Description:  根据id获取用户
     * @Param:  id
     * @return:  用户
     **/
    User getUserById(Integer id);


    /**
     * @Description:  修改用户信息
     * @Param:  用户
     * @return:
     **/
    void updateUser(User user);


    /**
     * @Description:  删除用户
     * @Param:  id
     * @return:
     **/
    void deleteUser(Integer id);


    /**
     * @Description:  添加用户
     * @Param:  用户
     * @return:
     **/
    User insertUser(User user);


    /**
     * @Description:  根据用户名或者邮箱查找用户
     * @Param:  用户名或者邮箱
     * @return:  用户
     **/
    User getUserByNameOrEmail(String str);


    /**
     * @Description:  通过名字获取用户
     * @Param:  名字
     * @return:  用户
     **/
    User getUserByName(String name);


    /**
     * @Description:  根据邮箱获取用户
     * @Param:  邮箱
     * @return:  用户
     **/
    User getUserByEmail(String email);
}
