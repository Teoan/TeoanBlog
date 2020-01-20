package com.teoan.blog.mapper;

import com.teoan.blog.entity.User;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/1/20 16:42
 */
public interface UserMapper {

    /**
     * @description: 通过id删除
     * @param:  用户id
     * @return:  影响行数
     */
    int deleteById(Integer userId);


    /**
     * @description: 根据id获取用户
     * @param:  用户id
     * @return:  用户
     */
    User getUserById(Integer userId);

    /**
     * @description: 添加
     * @param:  用户
     * @return:  影响行数
     */
    int insert(User user);

    /**
     * @description: 更新
     * @param:  用户
     * @return:  影响行数
     */
    int update(User user);

    /**
     * @description: 获取用户列表
     * @param:
     * @return:  用户列表
     */
    List<User> listUser();


    /**
     * @description: 根据邮箱或者名字获取用户
     * @param:  邮箱或名字
     * @return:  用户
     */
    User getUserByNameOrEmail(String str);

    /**
     * @description: 根据名字获取用户
     * @param:  用户名字
     * @return:  用户
     */
    User getUserByName(String str);


    /**
     * @description: 根据邮箱获取用户
     * @param:  邮箱
     * @return:  用户
     */
    User getUserByEmail(String str);
}
