package com.teoan.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.teoan.blog.entity.Article;
import com.teoan.blog.entity.Comment;
import com.teoan.blog.enums.ArticleStatus;
import com.teoan.blog.service.ArticleService;
import com.teoan.blog.service.CommentService;
import com.teoan.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/15 16:18
 */
@Controller
@RequestMapping("/admin/comment")
public class BackCommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;


    /**
     * @Description: 评论页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("")
    public String commentListView(@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                                  @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                                  Model model){
        PageInfo<Comment> commentPageInfo = commentService.listCommentByPage(pageIndex,pageSize);
        model.addAttribute("pageInfo",commentPageInfo);
        model.addAttribute("pageUrlPrefix","/admin/comment?pageIndex");
        return "Admin/Comment/index";
    }



    /**
     * @Description: 添加评论
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
//    @ResponseBody
    public void insterComment(HttpServletRequest request,Comment comment){
        //添加评论
        comment.setCommentIp(MyUtils.getIpAddr(request));
        comment.setCommentCreateTime(new Date());
        commentService.insertComment(comment);

        //更新文章评论数
//        Article article =  articleService.getArticleByStatusAndId(null,comment.getCommentArticleId());
//        articleService.updateCommentCount(article.getArticleId());
        articleService.updateCommentCount(comment.getCommentArticleId());
    }


    /**
     * @Description: 删除评论
     * @Param:
     * @return:
     **/
    @RequestMapping("/delete/{id}")
    public void deleteComment(@PathVariable("id") Integer id){
        Comment comment = commentService.getCommentById(id);
        //删除评论
        commentService.deleteComment(id);
        //删除子评论
        List<Comment> commentList = commentService.listChildComment(id);
        for (Comment comment1:commentList
             ) {
            commentService.deleteComment(comment.getCommentId());
        }
        //更新文章
        articleService.updateCommentCount(comment.getCommentArticleId());
    }


    /**
     * @Description: 编辑评论提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCommentSubmit(Comment comment){
        commentService.updateComment(comment);
        return "redirect:/admin/comment";
    }


    /**
     * @Description: 回复评论页面显示
     * @Param:  评论id
     * @return:
     **/
    @RequestMapping(value = "/reply/{id}")
    public String replyCommentView(@PathVariable("id") Integer id,Model model){
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment",comment);
        return "Admin/Comment/reply";
    }

    /**
     * @Description:  评论回复提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/replySubmit",method = RequestMethod.POST)
    public String replyCommentSubmit(HttpServletRequest request,Comment comment){

        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),comment.getCommentArticleId());
        article.setArticleCommentCount(article.getArticleCommentCount()+1);
        articleService.updateArticle(article);

        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        commentService.insertComment(comment);
        return "redirect:/admin/comment";
    }
}
