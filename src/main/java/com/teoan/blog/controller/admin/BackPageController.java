package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.Page;
import com.teoan.blog.enums.PageStatus;
import com.teoan.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Teoan
 * @description
 * @date 2020/2/17 14:36
 */
@Controller
@RequestMapping("/admin/page")
public class BackPageController {
    @Autowired
    private PageService pageService;


    /**
     * @Description: 后台页面列表显示
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "")
    public String index(Model model){

        List<Page> pageList = pageService.listPage(null);
        model.addAttribute("pageList",pageList);
        return "Admin/Page/index";
    }


    /**
     * @Description:  添加页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("/insert")
    public String insertPageView(){
        return "Admin/Page/insert";
    }


    /**
     * @Description: 插入页面提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertPageSubmit(Page page){

        //判断别名是否存在
        Page checkPage = pageService.getPageByKey(null,page.getPageKey());
        if (checkPage==null){
            page.setPageCreateTime(new Date());
            page.setPageUpdateTime(new Date());
            page.setPageStatus(PageStatus.NORMAL.getValue());
            pageService.insertPage(page);
        }
        return "redirect:/admin/page";
    }


    /**
     * @Description: 删除页面
     * @Param:
     * @return:
     **/
    @RequestMapping("/delete/{id}")
    public String deletePage(@PathVariable("id")Integer id){
        pageService.deletePage(id);
        return "redirect:/admin/page";
    }

    /**
     * @Description: 编辑页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("/edit/{id}")
    public String editPageView(@PathVariable("id") Integer id,Model model){
        Page page = pageService.getPageById(id);
        model.addAttribute("page",page);
        return "Admin/Page/edit";
    }


    /**
     * @Description:  编辑页面提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "editSubmit",method = RequestMethod.POST)
    public String editPageSubmit(Page page){
        Page checkPage = pageService.getPageById(page.getPageId());
        //判断别名是否存在且不是这篇文章
        if(Objects.equals(checkPage.getPageId(),page.getPageId())){

            page.setPageUpdateTime(new Date());
            pageService.updatePage(page);
        }
        return "redirect:/admin/page";
    }

}
