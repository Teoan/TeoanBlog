package com.teoan.blog.mapper;

import com.teoan.blog.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/1/20 13:56
 */
public interface PageMapper {
    /**
     * @description: 通过id删除
     * @param:  页面id
     * @return:  影响行数
     */
    int deleteById(Integer pageId);


    /**
     * @description: 添加
     * @param:  页面
     * @return:  影响行数
     */
    int insert(Page page);
    
    /** 
     * @description: 根据id查询 
     * @param:  页面id
     * @return:  页面
     */
    Page getPageById(Integer pageId);

    /**
     * @description: 更新
     * @param:  页面
     * @return:  影响行数
     */
    int update(Page page);


    /**
     * @description: 获取页面列表
     * @param: 状态
     * @return:  页面列表
     */
    List<Page> listPage(@Param(value = "status") Integer status);


    /**
     * @description: 根据key获取页面
     * @param:  状态
     * @param:  别名
     * @return:  页面
     */
    Page getPageByKey(@Param(value = "status") Integer status,@Param(value = "key")String key);





}
