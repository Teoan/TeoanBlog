package com.teoan.blog.service;

import com.github.pagehelper.PageInfo;
import com.teoan.blog.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Teoan
 * @description 评论服务接口
 * @date 2020/2/1 15:15
 */
@Service
public interface CommentService {

    /**
     * @Description:  添加评论
     * @Param:  评论
     * @return:
     **/
    void insertComment(Comment comment);


    /**
     * @Description: 根据文章id获取评论列表
     * @Param:  文章id
     * @return:  文章列表
     **/
    List<Comment> listCommentByArticleId(Integer articleId);


    /**
     * @Description: 根据id获取评论
     * @Param:  评论id
     * @return:
     **/
    Comment getCommentById(Integer id);


    /**
     * @Description: 获取所有评论列表
     * @Param: 第几页开始
     * @Param: 一页显示多少
     * @return:  列表
     **/
    PageInfo<Comment> listCommentByPage(
            Integer pageIndex,
            Integer pageSize);


    /**
     * @Description: 获得评论列表
     * @return:  列表
     **/
    List<Comment> listComment();


    /**
     * @Description: 删除评论
     * @Param:  评论id
     * @return:
     **/
    void deleteComment(Integer id);

    /**
     * @Description: 修改评论
     * @Param:  评论
     * @return:
     **/
    void updateComment(Comment comment);


    /**
     * @Description: 统计评论数
     * @Param:
     * @return:  评论数量
     **/
    Integer countComment();

    /**
     * @Description: 获得评论的子评论
     * @Param:  评论id
     * @return:  子评论列表
     **/
    List<Comment> listChildComment(Integer id);


    /**
     * @Description:  获得最新评论
     * @Param:  查询数量
     * @return:  列表
     **/
    List<Comment> listRecentComment(Integer limit);
}
