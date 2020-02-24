package com.teoan.blog.controller.home;

import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.teoan.blog.entity.*;
import com.teoan.blog.enums.ArticleStatus;
import com.teoan.blog.enums.LinkStatus;
import com.teoan.blog.enums.NoticeStatus;
import com.teoan.blog.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.asm.IModelFilter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @author Teoan
 * @description 用户的controller
 * @date 2020/2/6 21:40
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = {"/","/article"})
    public String index(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false,defaultValue = "10") Integer pageSize, Model model){

        HashMap<String,Object> criteria = new HashMap<>(1);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        //文章列表
        PageInfo<Article> articleList = articleService.pageArticle(pageIndex,pageSize,criteria);
        model.addAttribute("pageInfo",articleList);

        //公告
        List<Notice> notices = noticeService.listNotice(NoticeStatus.NORMAL.getValue());
        model.addAttribute("noticeList",notices);

        //友情链接
        List<Link> linkList = linkService.listLink(LinkStatus.NORMAL.getValue());
        model.addAttribute("linkList", linkList);

        //标签列表显示
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("allTagList", tagList);

        //最新评论
        List<Comment> commentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList", commentList);

        model.addAttribute("pageUrlPrefix", "/article?pageIndex");

        return "Home/index";
    }

    @RequestMapping(value = "/search")
    public String search(
            @RequestParam("keywords") String keywords,
            @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                         @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model){

        HashMap<String,Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        //防止xss
        keywords = HtmlUtil.escape(keywords);
        System.out.println(keywords);
        criteria.put("keywords",keywords);
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        //标签列表显示
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("allTagList", tagList);

        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", randomArticleList);


        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList", recentCommentList);
        model.addAttribute("keywords",keywords);
        model.addAttribute("pageUrlPrefix", "/search?pageIndex");
        return "Home/Page/search";
    }


    @RequestMapping("/404")
    public String NotFound(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "Home/Error/404";
    }

    @RequestMapping("/500")
    public String ServerError(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "Home/Error/500";
    }
}
