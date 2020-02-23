package com.teoan.blog.controller.home;

import com.teoan.blog.entity.Article;
import com.teoan.blog.entity.Link;
import com.teoan.blog.enums.LinkStatus;
import com.teoan.blog.service.ArticleService;
import com.teoan.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/8 15:36
 */
@Controller
public class LinkController {

    @Autowired
    private LinkService linkService;


    @RequestMapping("/applyLink")
    public String applyLinkView(){
        //侧边栏显示
        return "Home/Page/applyLink";
    }


    @RequestMapping(value = "/applyLinkSubmit",method = {RequestMethod.POST})
    @ResponseBody
    public void applyLinkSubmit(Link link)  {
        link.setLinkStatus(LinkStatus.HIDDEN.getValue());
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        linkService.insertLink(link);
    }
}
