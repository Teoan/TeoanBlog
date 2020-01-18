package com.teoan.blog.mapper;

import com.teoan.blog.entity.ArticleTagRef;
import com.teoan.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Teoan
 *文件标签关联Mapper
 * @date 2020/1/18 12:06
 */
@Mapper
public interface ArticleTagRefMapper {

    /**
     * @description: 添加文章标签和关联记录
     * @param:  关联对象
     * @return:  影响行数
     */
    Integer insert(ArticleTagRef articleTagRef);

    /**
     * @description: 根据标签id删除记录
     * @param:  标签id
     * @return:  影响行数
     */
    Integer deleteByTagId(Integer tagId);


    /**
     * @description: 根据文章id删除记录
     * @param:  文章id
     * @return:  影响行数
     */
    Integer deleteByArticleId(Integer ArticleId);

    /**
     * @description: 根据标签id统计文章数量
     * @param:  标签id
     * @return:  文章数量
     */
    Integer countArticleByTagId(Integer tagId);

    /**
     * @description: 根据文章获得标签列表
     * @param:  文章id
     * @return:  标签列表
     */
    List<Tag> listTagByArticleId(Integer articleId);
}
