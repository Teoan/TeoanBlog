package com.teoan.blog.mapper;

import com.teoan.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 　 @author Teoan
 *
 * @date 2020/1/17 12:29
 */
@Mapper
public interface ArticleMapper {

    /**
     * @Description: 根据id删除
     * @Param:  文章id
     * @return:  影响行数
     */
    Integer deleteById(Integer articleId);

    /**
     * @Description: 添加文章
     * @Param:  文章
     * @return:  影响行数
     */

    Integer insert(Article article);

    /**
     * @Description:  更新文章
     * @Param:  文章
     * @return:  影响行数
     */
    Integer update(Article article);

    /**
     * @Description: 获取所有文章
     * @Param:  criteria 查询条件
     * @return:  文章列表
     */
    List<Article> findAll(HashMap<String,Object> criteria);

    /**
     * @Description: 文章归档
     * @Param:
     * @return:
     */
    List<Article> lisAllNotWithConten();

    /**
     * @Description: 获取文章总数
     * @Param:  状态
     * @return:  数量
     */
    Integer countArticle(@Param(value = "status") Integer status);

    /**
     * @Description: 获取留言总数
     * @Param:
     * @return:  数量
     */
    Integer countArticleComment();

    /**
     * @Description: 获得浏览总数
     * @Param:
     * @return:  文章数量
     */
    Integer countArticleView();


    /**
     * @Description: 获取所有文章
     * @Param:
     * @return:
     */
    List<Article> listArticle();


    /**
     * @Description: 根据id查询用户信息
     * @Param:  status 状态
     * @Param:  id 文章id
     * @return:  文章
     */
    Article getArticleByStatusAndId(@Param(value = "status") Integer status, @Param(value = "id") Integer id);

    /**
     * @Description: 分页操作
     * @Param:  status 状态
     * @Param:  pageIndex 从第几页开始
     * @Param:  pageSize  数量
     * @return:  文章列表
     */
    @Deprecated
    List<Article> pageArticle(@Param(value = "status") Integer status,
                              @Param(value = "pageIndex") Integer pageIndex,
                              @Param(value = "pageSize") Integer pageSize);


    /** 
     * @Description: 获得访问最多的文章(猜你喜欢)
     * @Param:  limit 查询数量
     * @return:  文章列表
     */
    List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);


    /**
     * @Description: 获得上一篇文章
     * @param id  文章ID
     * @return:  文章
     */
    Article getAfterArticle(@Param(value = "id") Integer id);

    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(@Param(value = "id") Integer id);

    /**
     * @Description:  获得随机文章
     * @param limit 查询数量
     * @return:  文章列表
     */
    List<Article> listRandomArticle(@Param(value = "limit") Integer limit);

    /**
     * @Description: 热评文章
     * @param:  查询数量
     * @return:  文章列表
     */

    List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);

    /**
     * @Description: 最后更新的记录
     * @param:
     * @return:  文章
     */
    Article getLastUpdateArticle();


    /**
     * @Description:  用户的文章数
     * @param:  用户的id
     * @return:  数量
     */
    Integer countArticleByUser(@Param(value = "id") Integer id);

    /**
     * @Description: 根据分类id
     * @param limit 查询数量
     * @param categoryId 分类id
     * @return:  文章列表
     */
    List<Article> findArticleByCategoryId(@Param("categoryId") Integer categoryId,
                                          @Param("limit") Integer limit);


    /**
     * @Description: 根据分类id
     * @param categoryIds 分类id集合
     * @return:  文章列表
     */
    List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                           @Param("limit") Integer limit);


    /**
     * @Description: 获得最新的文章
     * @param limit 查询数量
     * @return:  文章列表
     */
    List<Article> listArticleByLimit(Integer limit);

    /**
     * @Description: 批量删除文章
     * @param ids 文章id集合
     * @return:  影响行数
     */
    Integer deleteBatch(@Param("ids") List<Integer> ids);


    /**
     * @Description 更新文章评论数
     * @Date 11:26 2020/1/23
     * @Param 文章id
     * @return
     **/
    void updateCommentCount(@Param(value = "articleId") Integer articleId);
}
