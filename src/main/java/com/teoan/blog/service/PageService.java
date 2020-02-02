package com.teoan.blog.service;

import com.teoan.blog.entity.Page;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/2 16:49
 */
public interface PageService {


    /**
     * @Description:  获得页面列表
     * @Param:  状态
     * @return:  页面列表
     **/
    List<Page> listPage(Integer status);


    /**
     * @Description: 根据页面key获得页面
     * @Param:  状态
     * @Param:  key
     * @return:
     **/
    Page getPageByKey(Integer status, String key);


    /**
     * @Description: 根据id获取页面
     * @Param:  页面id
     * @return:  页面
     **/
    Page getPageById(Integer id);



    /**
     * @Description: 添加页面
     * @Param:  页面
     * @return:
     **/
    void insertPage(Page page);


    /**
     * @Description:  删除页面
     * @Param:  页面id
     * @return:
     **/
    void deletePage(Integer id);



    /**
     * @Description:  更新页面
     * @Param:  页面
     * @return:
     **/
    void updatePage(Page page);
}
