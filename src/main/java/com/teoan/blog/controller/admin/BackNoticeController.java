package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.Notice;
import com.teoan.blog.enums.NoticeStatus;
import com.teoan.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/16 17:19
 */
@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {

    @Autowired
    private NoticeService noticeService;


    /**
     * @Description: 后台公告显示
     * @Param:
     * @return:
     **/
    @RequestMapping("")
    public String index(Model model){
        List<Notice> noticeList = noticeService.listNotice(null);
        model.addAttribute("noticeList", noticeList);
        return "Admin/Notice/index";
    }

    /**
     * @Description: 添加公告显示
     * @Param:
     * @return:
     **/
    @RequestMapping("/insert")
    public String insertNoticeView(){
        return "Admin/Notice/insert";
    }


    /**
     * @Description: 添加公告提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "insertSubmit",method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice){
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        notice.setNoticeStatus(NoticeStatus.NORMAL.getValue());
        notice.setNoticeOrder(1);
        noticeService.insertNotice(notice);
        return "redirect:/admin/notice";
    }


    /**
     * @Description: 删除公告
     * @Param:
     * @return:
     **/
    @RequestMapping("/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id){
        noticeService.deleteNotice(id);
        return "redirect:/admin/notice";
    }


    /**
     * @Description: 编辑页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("/edit/{id}")
    public String editNoticeView(@PathVariable("id") Integer id, Model model){
        List<Notice> noticeList=noticeService.listNotice(id);
        model.addAttribute("noticeList",noticeList);
        return "Admin/Notice/edit";
    }


    /**
     * @Description: 编辑页面提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice){
        notice.setNoticeUpdateTime(new Date());
        noticeService.updateNotice(notice);
        return "redirect:/admin/notice";
    }

}
