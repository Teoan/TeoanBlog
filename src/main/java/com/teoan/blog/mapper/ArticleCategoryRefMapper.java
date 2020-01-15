package com.teoan.blog.mapper;

import com.teoan.blog.entity.ArticleCategoryRef;
import com.teoan.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 　 @author Teoan
 *
 * @date 2020/1/15 19:34
 */
@Mapper
public interface ArticleCategoryRefMapper {
    /**
     * @Description: 添加文章分类关联记录
     * @Param:  articleCategoryRef 关联对象
     * @return:  影响行数
     */
    int inster(ArticleCategoryRef articleCategoryRef);


    /**
     * @Description: 根据分类id删除记录
     * @Param:  categoryId 分类id
     * @return:  影响行数
     */
    int deleteByCategoryId(Integer categoryId);

    /**
     * @Description: 根据文章ID删除记录
     * @Param: articleId
     * @return: 影响行数
     */
    int deleteByArticleId(Integer articleId);

    /**
     * @Description:  根据分类ID统计文章数
     * @Param:  categoryId 分类ID
     * @return:  文章数量
     */
    int countArticleByCategoryId(Integer categoryId);


    /**
     * @Description:  根据文章ID查询分类ID
     * @Param:  articleId 文章ID
     * @return: 分类ID列表
     */
    List<Integer> selectCategoryIdByArticleId(Integer articleId);


    /**
     * @Description: 根据分类ID查询文章ID
     * @Param:  categoryId 分类ID
     * @return:  文章ID列表
     */
    List<Integer> selectArticleIdByCategoryId(Integer categoryId);


    /**
     * @Description:  根据文章ID获得分类列表
     * @Param:  articleId 文章ID
     * @return:  分类列表
     */
    List<Category> listCategoryByArticleId(Integer articleId);
}
