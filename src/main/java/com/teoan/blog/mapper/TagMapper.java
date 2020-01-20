package com.teoan.blog.mapper;

import com.teoan.blog.entity.Tag;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/1/20 15:56
 */
public interface TagMapper {

    /**
     * @description:  根据id获取标签
     * @param:  标签id
     * @return:  标签
     */
    Tag getTagById(Integer tagId);

    /**
     * @description: 通过id删除
     * @param:  标签id
     * @return:  影响行数
     */
    int deleteById(Integer tagId);

    /**
     * @description: 添加
     * @param:  标签
     * @return:  影响行数
     */
    int insert(Tag tag);

    /**
     * @description: 更新
     * @param:  标签
     * @return:  影响行数
     */
    int update(Tag tag);

    /**
     * @description: 获得标签列表
     * @param:
     * @return:  标签列表
     */
    List<Tag> listTag();

    /**
     * @description: 获取标签总数
     * @param:
     * @return:  标签总数
     */
    Integer countTag();


    /**
     * @description: 根据标签名获取标签
     * @param:  标签名
     * @return:  标签
     */
    Tag getTagByName(String tagName);
}
