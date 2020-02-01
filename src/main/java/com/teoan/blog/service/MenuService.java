package com.teoan.blog.service;

import com.teoan.blog.entity.Menu;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/1 16:47
 */
public interface MenuService {


    /**
     * @Description: 获取菜单列表
     * @Param:
     * @return:  菜单列表
     **/
    List<Menu> listMenu() ;


    /**
     * @Description: 添加菜单项目
     * @Param:  菜单
     * @return:  菜单
     **/
    void insertMenu(Menu menu) ;


    /**
     * @Description: 删除菜单项目
     * @Param:  菜单id
     * @return:
     **/
    void deleteMenu(Integer id) ;

    /**
     * @Description: 更新菜单项目
     * @Param:  菜单
     * @return:
     **/
    void updateMenu(Menu menu) ;


    /**
     * @Description: 根据菜单id获取菜单
     * @Param:  菜单id
     * @return:
     **/
    Menu getMenuById(Integer id) ;
}
