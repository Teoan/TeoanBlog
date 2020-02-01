package com.teoan.blog.service;

import com.teoan.blog.entity.Link;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/1 16:33
 */
@Service
public interface LinkService {


    /**
     * @Description: 获得链接总数
     * @Param:  状态
     * @return:  数量
     **/
    Integer countLink(Integer status);


    /**
     * @Description: 获得链接列表
     * @Param:  状态
     * @return:  链接列表
     **/
    List<Link> listLink(Integer status);


    /**
     * @Description: 添加链接
     * @Param:  链接
     * @return:
     **/
    void insertLink(Link link);


    /**
     * @Description:  删除链接
     * @Param:  id
     * @return:
     **/
    void deleteLink(Integer id);


    /**
     * @Description:  更新链接
     * @Param:  链接
     * @return:
     **/
    void updateLink(Link link);


    /**
     * @Description:  根据id获取链接
     * @Param:  id
     * @return:  链接
     **/
    Link getLinkById(Integer id);
}
