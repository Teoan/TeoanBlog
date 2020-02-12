package com.teoan.blog.controller.home;

import com.alibaba.fastjson.JSON;
import com.teoan.blog.entity.Article;
import com.teoan.blog.entity.Comment;
import com.teoan.blog.entity.Tag;
import com.teoan.blog.entity.User;
import com.teoan.blog.enums.ArticleStatus;
import com.teoan.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/3 17:18
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;




    /**
     * @Description: 文章详情页显示
     * @Param:  文章id
     * @return:  modelAndView
     **/
    @RequestMapping(value = "/article/{articleId}")
    public String getArticleDetailPage(@PathVariable("articleId") Integer articleId, Model model){

        //获取文章信息 分类 标签 作者 评论
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),articleId);

        if(article == null){
            return "Home/Error/404";
        }


        //用户信息
        User user = userService.getUserById(article.getArticleUserId());
        article.setUser(user);

        //文章信息
        model.addAttribute("article",article);

        //评论列表
        List<Comment> comments = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList",comments);


        //相关文章
        List<Integer> categoryIds = articleService.listCategoryIdByArticleId(articleId);
        List<Article> articleList = articleService.listArticleByCategoryIds(categoryIds,5);
        model.addAttribute("similarArticleList", articleList);

        //猜你喜欢
        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);
        model.addAttribute("mostViewArticleList", mostViewArticleList);

        //获取下一篇文章
        Article afterArticle = articleService.getAfterArticle(articleId);
        model.addAttribute("afterArticle", afterArticle);

        //获取上一篇文章
        Article preArticle = articleService.getPreArticle(articleId);
        model.addAttribute("preArticle", preArticle);

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);

        return "Home/Page/articleDetail";
    }


    /**
     * @Description:  点赞增加
     * @Param:  文章id
     * @return:  点赞量的数量
     **/
    @RequestMapping(value = "/article/like/{id}", method = {RequestMethod.POST})
    @ResponseBody
    public String increaseLikeCount(@PathVariable("id") Integer id){
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),id);
        Integer likeCount = article.getArticleLikeCount()+1;
        article.setArticleLikeCount(likeCount);
        articleService.updateArticle(article);
        return JSON.toJSONString(likeCount);
    }

    /**
     * @Description:  文章访问数量增加
     * @Param:  文章id
     * @return:  访问数量
     **/
    @RequestMapping(value = "/article/view/{id}", method = {RequestMethod.POST})
    @ResponseBody
    public String increaseViewCount(@PathVariable("id") Integer id){
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),id);
        Integer viewCount = article.getArticleViewCount()+1;
        article.setArticleViewCount(viewCount);
        articleService.updateArticle(article);
        return JSON.toJSONString(viewCount);
    }
}
