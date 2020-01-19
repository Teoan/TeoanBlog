package com.teoan.blog.mapper;

import com.teoan.blog.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/1/19 17:25
 */
@Mapper
public interface MenuMapper {

    /**
     * @description: 通过id删除菜单
     * @param:  菜单id
     * @return:  影响行数
     */
    int deleteById(Integer menuId);

    /**
     * @description: 添加
     * @param:  菜单
     * @return:  影响行数
     */
    int insert(Menu menu);

    /**
     * @description: 根据id获取菜单
     * @param:  菜单id
     * @return:  菜单
     */
    Menu getMenuById(Integer menuId);

    /**
     * @description: 更新
     * @param:  菜单
     * @return:  影响行数
     */
    int update(Menu menu);


    /**
     * @description: 获得菜单列表
     * @param:
     * @return:  菜单列表
     */
    List<Menu> listMenu() ;



}
