package com.teoan.blog.controller.home;

import com.teoan.blog.entity.Article;
import com.teoan.blog.entity.Category;
import com.teoan.blog.entity.Page;
import com.teoan.blog.entity.Tag;
import com.teoan.blog.service.ArticleService;
import com.teoan.blog.service.CategoryService;
import com.teoan.blog.service.PageService;
import com.teoan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/8 16:51
 */
@Controller
public class PageController {
    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    /**
     * @Description: 页面详情页面
     * @Param:  key
     * @return:  model
     **/
    @RequestMapping(value = "/{key}")
    public String pageDetail(@PathVariable("key") String key, Model model){
        Page page = pageService.getPageByKey(1,key);
        if (page==null){
            return "redirect:/404";
        }
        model.addAttribute("page",page);

        //侧边栏显示
        return "Home/Page/page";
    }

    /**
     * @Description: 文章归档页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/articleFile")
    public String articleFile(Model model) {
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);
        //侧边栏显示
        return "Home/Page/articleFile";
    }


    /**
     * @Description: 站点地图显示
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/map")
    public String siteMap(Model model) {
        //文章显示
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);
        //分类显示
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);
        //标签显示
        List<Tag> tagList = tagService.listTag();
        model.addAttribute("tagList", tagList);

        return "Home/Page/siteMap";

    }

    /**
     * @Description:  留言板
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/message")
    public String message(Model model) {

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList", mostCommentArticleList);
        return "Home/Page/message";
    }
}
