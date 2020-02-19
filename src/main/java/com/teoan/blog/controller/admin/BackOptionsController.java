package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.Options;
import com.teoan.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



/**
 * @author Teoan
 * @description
 * @date 2020/2/16 23:20
 */
@Controller
@RequestMapping("/admin/options")
public class BackOptionsController {

    @Autowired
    private OptionsService optionsService;


    /**
     * @Description: 显示基本信息
     * @Param:
     * @return:
     **/
    @RequestMapping("")
    public String index(Model model){
        Options option = optionsService.getOptions();

        model.addAttribute("option",option);
        return "Admin/Options/index";
    }



    /**
     * @Description: 显示编辑页面
     * @Param:
     * @return:
     **/
    @RequestMapping("/edit")
    public String editOptionView(Model model){
        Options option = optionsService.getOptions();

        model.addAttribute("option",option);
        return "Admin/Options/index";
    }


    @RequestMapping(value = "editSubmit",method = RequestMethod.POST)
    public String editOptionSubmit(Options options){
        Options optionsCustom = optionsService.getOptions();
        if(optionsCustom.getOptionId()==null) {
            optionsService.insertOptions(options);
        } else {
            optionsService.updateOptions(options);
        }
        return "redirect:/admin/options";
    }
}
