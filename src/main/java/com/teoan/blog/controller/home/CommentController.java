package com.teoan.blog.controller.home;

import cn.hutool.http.HtmlUtil;
import com.teoan.blog.dto.JsonResult;
import com.teoan.blog.entity.Article;
import com.teoan.blog.entity.Comment;
import com.teoan.blog.enums.ArticleStatus;
import com.teoan.blog.enums.Role;
import com.teoan.blog.service.ArticleService;
import com.teoan.blog.service.CommentService;
import com.teoan.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Teoan
 * @description
 * @date 2020/2/6 12:26
 */
@Controller
@RestController  //RestController使用的效果是将方法返回的对象直接在浏览器上展示成json格式，而如果单单使用@Controller会报错，需要ResponseBody配合使用。
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    /**
     * @Description: 添加评论
     **/

    @RequestMapping(value = "/comment", method = {RequestMethod.POST})
    public JsonResult insertComment(HttpServletRequest request, Comment comment){
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));

        if(request.getSession().getAttribute("user")!=null){
            comment.setCommentRole(Role.ADMIN.getValue());
        }else {
            comment.setCommentRole(Role.VISITOR.getValue());
        }

        comment.setCommentAuthorAvatar(MyUtils.getGravatar(comment.getCommentAuthorEmail()));

        //过滤与html有关的字符，防止XSS攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
        comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));
        comment.setCommentAuthorUrl(HtmlUtil.escape(comment.getCommentAuthorUrl()));
        try {
            commentService.insertComment(comment);
            //更新文章评论数
//            Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),comment.getCommentArticleId());
            articleService.updateCommentCount(comment.getCommentArticleId());
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult().fail();
        }
        return new JsonResult().ok();
    }

}
