package com.teoan.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.teoan.blog.entity.Article;
import com.teoan.blog.entity.Comment;
import com.teoan.blog.enums.ArticleStatus;
import com.teoan.blog.mapper.ArticleMapper;
import com.teoan.blog.mapper.CommentMapper;
import com.teoan.blog.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/1 15:29
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;


    private Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Override
    public void insertComment(Comment comment) {
        try{
            commentMapper.insert(comment);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("创建评论失败：comment:{}, cause:{}", comment, e);
        }
    }

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        List<Comment> comments = null;
        try {
            comments=commentMapper.listCommenntByArticleId(articleId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据文章ID获得评论列表失败，articleId:{},cause:{}", articleId, e);
        }
       return comments;
    }

    @Override
    public Comment getCommentById(Integer id) {
        Comment comment = null;
        try {
            comment = commentMapper.getCommentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据评论ID获得评论，id:{}, cause:{}", id, e);
        }
        return comment;
    }

    @Override
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Comment> comments = null;
        try {
            comments = commentMapper.listComment();
            for(Comment comment:comments){
                Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),comment.getCommentArticleId());
                comment.setArticle(article);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("分页获得评论失败,pageIndex:{}, pageSize:{}, cause:{}", pageIndex, pageSize, e);
        }
        return new PageInfo<>(comments);
    }

    @Override
    public List<Comment> listComment() {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得评论列表失败：cause:{}", e);
        }
        return commentList;
    }

    @Override
    public void deleteComment(Integer id) {
        try {
            commentMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除评论失败, id:{}, cause:{}", id, e);
        }
    }

    @Override
    public void updateComment(Comment comment) {
        try {
            commentMapper.update(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新评论，comment:{}, cause:{}", comment, e);
        }
    }

    @Override
    public Integer countComment() {
        Integer commentCount = null;
        try {
            commentCount = commentMapper.countComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计评论数量失败, cause:{}", e);
        }
        return commentCount;
    }

    @Override
    public List<Comment> listRecentComment(Integer limit) {
        List<Comment> comments = null;
        try{
            comments = commentMapper.listRecentComment(limit);
            for (Comment comment:comments){
                Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),comment.getCommentArticleId());
                comment.setArticle(article);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("获得最新评论失败, limit:{}, cause:{}", limit, e);
        }
        return comments;
    }

    @Override
    public List<Comment> listChildComment(Integer id) {
        List<Comment> childCommentList = null;
        try {
            childCommentList = commentMapper.listChildComment(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得子评论失败, id:{}, cause:{}", id, e);
        }
        return childCommentList;
    }
}
