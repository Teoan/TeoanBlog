package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.Link;
import com.teoan.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/16 14:40
 */
@Controller
@RequestMapping("/admin/link")
public class BackLinkController {

    @Autowired
    private LinkService linkService;


    /**
     * @Description: 链接页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("")
    public ModelAndView linkList(){
        ModelAndView modelAndView = new ModelAndView();

        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);
        modelAndView.setViewName("Admin/Link/index");

        return modelAndView;
    }


    /**
     * @Description: 后台添加链接页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("/insert")
    public ModelAndView insertLinkView(){
        ModelAndView modelAndView = new ModelAndView("Admin/Link/insert");
        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);
        return modelAndView;
    }



    /**
     * @Description: 删除链接
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/delete/{id}")
    public String deleteLink(@PathVariable("id")Integer id){
        linkService.deleteLink(id);
        return "redirect:/admin/link";
    }


    /**
     * @Description: 后台编辑链接页面
     * @Param:
     * @return:
     **/
    @RequestMapping("/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView("Admin/Link/edit");

        //获得链接属性
        Link linkCustom =  linkService.getLinkById(id);
        modelAndView.addObject("linkCustom",linkCustom);

        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);
        return modelAndView;
    }

    /**
     * @Description:  编辑链接提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editLinkSubmit(Link link)  {
        link.setLinkUpdateTime(new Date());
        linkService.updateLink(link);
        return "redirect:/admin/link";
    }

}
