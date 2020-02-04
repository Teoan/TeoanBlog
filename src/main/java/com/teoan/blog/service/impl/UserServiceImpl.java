package com.teoan.blog.service.impl;

import com.teoan.blog.entity.User;
import com.teoan.blog.mapper.ArticleMapper;
import com.teoan.blog.mapper.UserMapper;
import com.teoan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/2 19:34
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;


    @Override
    public List<User> listUser() {
        List<User> users= null;
        users = userMapper.listUser();
        for(User user:users){
            Integer count = articleMapper.countArticleByUser(user.getUserId());
            user.setArticleCount(count);
        }
        return users;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public User insertUser(User user) {
        user.setUserRegisterTime(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    public User getUserByNameOrEmail(String str) {
        return userMapper.getUserByNameOrEmail(str);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }
}
