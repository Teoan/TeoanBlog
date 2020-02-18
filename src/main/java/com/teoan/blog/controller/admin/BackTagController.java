package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.Tag;
import com.teoan.blog.service.ArticleService;
import com.teoan.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/17 16:11
 */
@Controller
@RequestMapping("/admin/tag")
public class BackTagController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;


    /**
     * @Description: 显示标签列表
     * @Param:
     * @return:
     **/
    @RequestMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("Admin/Tag/index");

        List<Tag> tagList = tagService.listTag();

        modelAndView.addObject("tagList",tagList);

        return modelAndView;
    }

    /**
     * @Description: 添加页面提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag){
        tagService.insertTag(tag);
        return "redirect:/admin/tag";
    }


    /**
     * @Description: 删除标签
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id)  {
        Integer count = articleService.countArticleByTagId(id);
        if (count == 0) {
            tagService.deleteTag(id);
        }
        return "redirect:/admin/tag";
    }


    /**
     * @Description:  编辑标签页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editTagView(@PathVariable("id") Integer id)  {
        ModelAndView modelAndView = new ModelAndView("Admin/Tag/edit");

        Tag tag =  tagService.getTagById(id);
        modelAndView.addObject("tag",tag);

        List<Tag> tagList = tagService.listTagWithCount();
        modelAndView.addObject("tagList",tagList);
        return modelAndView;
    }

    /**
     * @Description: 标签提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editTagSubmit(Tag tag)  {
        tagService.updateTag(tag);
        return "redirect:/admin/tag";
    }
}
