package com.teoan.blog.mapper;

import com.teoan.blog.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/1/19 16:15
 */
@Mapper
public interface LinkMapper {

    /**
     * @description: 删除
     * @param:  链接id
     * @return:  影响行数
     */
    int deleteById(Integer linkId);

    /**
     * @description: 添加
     * @param:  链接
     * @return:  影响行数
     */
    int insert(Link link);
    /**
     * @description:  根据id获取链接对象
     * @param:  链接id
     * @return:  链接
     */
    Link getLinkById(Integer linkId);

    /**
     * @description: 更新
     * @param:  链接
     * @return:  影响行数
     */
    int update(Link link);

    /**
     * @description: 获取链接总数
     * @param:  状态
     * @return:  数量
     */
    Integer countLink(@Param(value = "status") Integer status);



    /**
     * @description: 获取链接列表
     * @param:  状态
     * @return:  列表
     */
    List<Link> listLink(@Param(value = "status") Integer status);
}
