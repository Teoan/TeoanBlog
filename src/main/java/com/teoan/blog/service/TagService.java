package com.teoan.blog.service;

import com.teoan.blog.entity.Tag;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/2 17:04
 */
public interface TagService {

    /**
     * @Description: 获得标签总数
     * @Param:
     * @return:  标签总数
     **/
    Integer countTag();


    /**
     * @Description:  获得标签列表
     * @Param:
     * @return:  标签列表
     **/
    List<Tag> listTag() ;


    /**
     * @Description: 获取标签列表
     * @Param:
     * @return: 标签列表
     **/
    List<Tag> listTagWithCount() ;

    /**
     * @Description: 通过id获取标签
     * @Param:  id
     * @return:  标签
     **/
    Tag getTagById(Integer id) ;


    /**
     * @Description:  添加标签
     * @Param:  标签
     * @return:
     **/
    void insertTag(Tag tag) ;


    /**
     * @Description:  更新标签
     * @Param:  标签
     * @return:
     **/
    void updateTag(Tag tag) ;


    /**
     * @Description:  删除标签
     * @Param:  标签id
     * @return:
     **/
    void deleteTag(Integer id);


    /**
     * @Description:  根据名字获取标签
     * @Param:  标签名字
     * @return:  标签
     **/
    Tag getTagByName(String name) ;

    /**
     * @Description: 根据文章id获取标签
     * @Param:  文章id
     * @return:  标签列表
     **/
    List<Tag> listTagByArticleId(Integer articleId);
}
