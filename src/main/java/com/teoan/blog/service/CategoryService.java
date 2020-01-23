package com.teoan.blog.service;

import com.teoan.blog.entity.Category;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/1/23 15:42
 */
public interface CategoryService {

    /**
     * @Description 获得分类总数
     * @Param
     * @return
     **/
    Integer countCategory();

    /**
     * @Description 获得分类列表
     * @Param
     * @return 分类列表
     **/
    List<Category> ListCategory();


    /**
     * @Description 获得分类列表
     * @Param
     * @return 分类列表
     **/
    List<Category> listCategoryWithCount();


    /**
     * @Description 删除分类
     * @Param 分类id
     * @return
     **/
    void deleteCategory(Integer id);


    /**
     * @Description 根据id查询分类
     * @Param 查询id
     * @return 分类
     **/
    Category getCategoryById(Integer id);


    /**
     * @Description 更新分类
     * @Param 分类
     * @return
     **/
    void updateCategory(Category category);


    /**
     * @Description 通过名字查找分类
     * @Param 分类名字
     * @return 分类
     **/
    Category getCategoryByName(String name);


    /**
     * @Description 添加
     * @Param 分类
     * @return
     **/
    void insertCategory(Category category);

}
