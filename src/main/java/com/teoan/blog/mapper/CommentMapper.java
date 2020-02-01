package com.teoan.blog.mapper;

import com.teoan.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/1/19 12:07
 */
@Mapper
public interface CommentMapper {

    /**
     * @description: 根据id删除
     * @param:  id
     * @return:  影响行数
     */
    int deleteById(Integer commentId);

    /**
     * @description: 添加
     * @param:  评论
     * @return:  影响行数
     */
    int insert(Comment comment);

    /**
     * @description: 根据id查询
     * @param:  评论id
     * @return:  评论
     */
    Comment getCommentById(Integer commentId);

    /**
     * @description: 更新评论
     * @param:  评论
     * @return:  影响行数
     */
    int update(Comment comment);


    /**
     * @description: 根据文章id获取评论列表
     * @param:  文章id
     * @return:  评论列表
     */
    List<Comment> listCommenntByArticleId(Integer articleId);

    /**
     * @description: 获取评论列表
     * @param:
     * @return:  列表
     */
    List<Comment> listComment();

    /**
     * @description: 统计评论数
     * @param:
     * @return:  数量
     */
    Integer countComment();

    /**
     * @description: 获得最近评论
     * @param:  查询数量
     * @return:  文章列表
     */
    List<Comment> listRecentComment(@Param(value = "limit") Integer limit);

    /**
     * @description: 获得评论的子评论
     * @param:  评论id
     * @return:  列表
     */
    List<Comment> listChildComment(@Param(value = "id")Integer id);

}
