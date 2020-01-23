package com.teoan.blog.mapper;

import com.teoan.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Teoan
 * @description 分类映射接口
 * @date 2020/1/18 15:42
 */
@Mapper
public interface CategoryMapper {
    /**
     * @description 添加
     * @param category  分类
     * @return:  影响行数
     */
    int insert(Category category);

    /**
     * @description: 更新
     * @param category 分类
     * @return:  影响行数
     */
    int update(Category category);
    /**
     * @description: 根据分类id获取分类信息
     * @param:  分类id
     * @return:  分类
     */
    Category getCategoryById(Integer id);

    /**
     * @description: 根据id删除分类
     * @param id 分类id
     * @return:  影响行数
     */
    int deleteCategory(Integer id);
    /**
     * @description: 查询分类总数
     * @param:
     * @return:  分类总数
     */
    Integer countCategory();

    /**
     * @description: 获取分类列表
     * @param:
     * @return:  分类列表
     */
    List<Category> listCategort();

    /**
     * @description: 根据父分类查找子分类
     * @param:  分类id
     * @return:  分类列表
     */
    List<Category> findChildCategory(@Param(value = "id") Integer id);

    /**
     * @description: 根据分类名获取分类
     * @param:  分类名
     * @return:  分类
     */
    Category getCategpryByName(String name);
}
