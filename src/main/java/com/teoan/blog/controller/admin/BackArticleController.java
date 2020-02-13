package com.teoan.blog.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.teoan.blog.dto.ArticleParam;
import com.teoan.blog.entity.Article;
import com.teoan.blog.entity.Category;
import com.teoan.blog.entity.Tag;
import com.teoan.blog.entity.User;
import com.teoan.blog.service.ArticleService;
import com.teoan.blog.service.CategoryService;
import com.teoan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/12 22:21
 */
@Controller
@RequestMapping("/admin/article")
public class BackArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;


    /**
     * @Description: 后台文章显示
     * @Param:
     * @return:
     **/
    @RequestMapping("")
    public String index(@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                        @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                        @RequestParam(required = false)String status, Model model){
        HashMap<String, Object> criteria = new HashMap<>(1);
        if (status == null){
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex");
        }else {
            criteria.put("status",status);
            model.addAttribute("pageUrlPrefix", "/admin/article?pageIndex?status="+status+"&pageIndex");
        }
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex,pageSize,criteria);
        model.addAttribute("pageInfo",articlePageInfo);
        return "Admin/Article/index";
    }

    /**
     * @Description: 添加文章页面
     * @Param:
     * @return:
     **/
    public String insertArticleView(Model model){
        List<Tag> tagList = tagService.listTag();
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("tagList",tagList);
        model.addAttribute("categoryList",categoryList);
        return "Admin/Article/insert";
    }

    /**
     * @Description: 后台添加文章
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertArticleSubmit(HttpSession session, ArticleParam articleParam){
        Article article = new Article();

        User user = (User) session.getAttribute("user");

        if(user!=null){
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());

        //文章摘要
        //设置摘要长度
        int summaryLength = 150;
        //防止XSS攻击
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (summaryText.length()>summaryLength){
            summaryText = summaryText.substring(0,summaryLength);
        }
        article.setArticleSummary(summaryText);
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());

        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleParentCategoryId()!=null){
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId()!=null){
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds()!=null){
            for (int tagId:articleParam.getArticleTagIds()){
                tagList.add(new Tag(tagId));
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        return "redirect:/admin/article";
    }


    /**
     * @Description: 删除文章
     * @Param:  文章id
     * @return:
     **/
    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer id){
        articleService.deleteArticle(id);
    }

    /**
     * @Description: 编辑文章页面
     * @Param:
     * @return:
     **/
    @RequestMapping("/edit/{id}")
    public ModelAndView editArticleView(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();

        Article article = articleService.getAfterArticle(id);
        modelAndView.addObject("article",article);

        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList",categoryList);

        List<Tag> tagList = tagService.listTag();
        modelAndView.addObject("tagList",tagList);

        modelAndView.setViewName("Admin/Article/edit");
        return modelAndView;
    }


    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam){
        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());

        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleParentCategoryId()!=null){
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId()!=null){
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds()!=null){
            for (int tagId:articleParam.getArticleTagIds()){
                tagList.add(new Tag(tagId));
            }
        }
        article.setTagList(tagList);

        articleService.updateArticle(article);
        return "redirect:/admin/article";
    }
}
