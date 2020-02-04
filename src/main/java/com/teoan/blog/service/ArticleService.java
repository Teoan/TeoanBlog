package com.teoan.blog.service;

import com.github.pagehelper.PageInfo;
import com.teoan.blog.entity.Article;

import java.util.HashMap;
import java.util.List;

/**
 * @author Teoan
 * @description 文章Service实现
 * @date 2020/1/21 11:18
 */

public interface ArticleService {


    /**
     * @description: 获取文章总数
     * @param:  状态
     * @return:  文章总数
     */
    Integer countArticle(Integer status);


    /**
     * @description: 获取评论总数
     * @param:
     * @return:  数量
     */
    Integer countArticleComment();

    /**
     * @description: 获取浏览量总数
     * @param:
     * @return:  总数
     */
    Integer countArticleView();


    /**
     * @description:  统计有这个分类的文章数
     * @param:  分类ID
     * @return:  数量
     */
    Integer countArticleByCategoryId(Integer categoryId);


    /**
     * @description:  统计有这个标签的文章数
     * @param:  标签id
     * @return:  数量
     */
    Integer countArticleByTagId(Integer tagId);


    /**
     * @description: 不分页获得所有文章
     * @param:  查询条件
     * @return:  列表
     */
    List<Article> listArticle(HashMap<String, Object> criteria);


    /**
     * @description  获取文章
     * @param limit 查询数量
     * @return  文章列表
     */
    List<Article> listRecentArticle(Integer limit);


    /**
     * @description: 修改文章详情
     * @param:  文章
     * @return:
     */
    void updateArticleDetail(Article article);


    /**
     * @description:  修改文章简单信息
     * @param:  文章
     * @return:
     */
    void updateArticle(Article article);


    /**
     * @description: 批量删除文章
     * @param:  文章id
     * @return:
     */
    void deleteArticleBatch(List<Integer> ids);


    /**
     * @description: 删除文章
     * @param:  文章id
     * @return:
     */
    void deleteArticle(Integer id);

    /**
     * @description:  分页显示
     * @param pageIndex 第几页开始
     * @param pageSize 一页显示多少
     * @param criteria 查询条件
     * @return:
     */
    PageInfo<Article> pageArticle(Integer pageIndex,
                                  Integer pageSize,
                                  HashMap<String, Object> criteria);


    /**
     * @description: 文章详情页显示
     * @param status 状态
     * @param id 文章id
     * @return 文章
     */
    Article getArticleByStatusAndId(Integer status, Integer id);



    /**
     * @Description: 获得访问最多的文章(猜你喜欢)
     * @Param limit 查询数量
     * @return:  文章列表
     */
    List<Article> listArticleByViewCount(Integer limit);


    /**
     * @description:  获得上一篇文章
     * @param:  文章id
     * @return:  文章
     */
    Article getAfterArticle(Integer id);


    /**
     * @description: 获得下一篇文章
     * @param id 文章id
     * @return:  文章
     */
    Article getPreArticle(Integer id);

    /**
     * @description: 获得随机文章
     * @param limit 查询数量
     * @return:  文章列表
     */
    List<Article> listRandomArticle(Integer limit);

    /**
     * @description:  获得评论数较多的文章
     * @param limit 查询数量
     * @return:  列表
     */
    List<Article> listArticleByCommentCount(Integer limit);


    /**
     * @description: 添加文章
     * @param:  文章
     * @return:
     */
    void insertArticle(Article article);

    /**
     * @description: 更新文章评论数
     * @param articleId 文章id
     * @return:
     */
    void updateCommentCount(Integer articleId);


    /**
     * @description: 获得最后更新记录
     * @param:
     * @return:  文章
     */
    Article getLastUpdateArticle();


    /**
     * @description: 获得相关文章
     * @param cateId 分类id
     * @param limit 查询数量
     * @return:  列表
     */
    List<Article> listArticleByCategoryId(Integer cateId, Integer limit);


    /**
     * @description: 获得相关文章
     * @param cateIds 分类id集合
     * @param limit 查询数量
     * @return:  列表
     */
    List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit);


    /**
     * @description:  根据文章ID获得分类ID列表
     * @param articleId 文章id
     * @return:  列表
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);


    /**
     * @description: 获得所有文章
     * @param:
     * @return:  列表
     */
    List<Article> listAllNotWithContent();

}
