package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.Link;
import com.teoan.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String linkList(Model model){

        List<Link> linkList = linkService.listLink(null);
        model.addAttribute("linkList",linkList);

        return "Admin/Link/index";
    }


    /**
     * @Description: 后台添加链接页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("/insert")
    public String insertLinkView(Model model){
        List<Link> linkList = linkService.listLink(null);
        model.addAttribute("linkList",linkList);
        return "Admin/Link/insert";
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
    public String editLinkView(@PathVariable("id") Integer id,Model model){

        //获得链接属性
        Link linkCustom =  linkService.getLinkById(id);
        model.addAttribute("linkCustom",linkCustom);

        List<Link> linkList = linkService.listLink(null);
        model.addAttribute("linkList",linkList);

        return "Admin/Link/edit";
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
