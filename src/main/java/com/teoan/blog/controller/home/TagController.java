package com.teoan.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.teoan.blog.entity.Article;
import com.teoan.blog.entity.Tag;
import com.teoan.blog.enums.ArticleStatus;
import com.teoan.blog.service.ArticleService;
import com.teoan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * @author Teoan
 * @description 标签Controller类
 * @date 2020/2/10 14:16
 */
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    /**
     * @Description: 根据标签获取文章
     * @Param: 标签id
     * @return:  视图模板
     **/
    @RequestMapping(value = "/tag/{tagId}")
    public String getArticleListByTag(@PathVariable("tagId") Integer tagId,
                                      @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model){
        Tag tag = tagService.getTagById(tagId);
        if (tag==null){
            return "redirect:/404";
        }
        model.addAttribute("tag",tag);

        //获取文章列表
        HashMap<String,Object> criteria = new HashMap<>(2);
        criteria.put("tagId",tagId);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex,pageSize,criteria);
        model.addAttribute("pageInfo",articlePageInfo);

        //标签侧边栏显示
        List<Tag> allTagList = tagService.listTag();
        model.addAttribute("allTagList",allTagList);

        //随机文章显示
        List<Article> articleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList", articleList);


        model.addAttribute("pageUrlPrefix", "/tag?pageIndex");

        return "Home/Page/articleListByTag";

    }


}
