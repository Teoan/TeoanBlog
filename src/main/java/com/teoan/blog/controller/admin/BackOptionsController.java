package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.Options;
import com.teoan.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("Admin/Options/index");
        Options option = optionsService.getOptions();

        modelAndView.addObject("option",option);
        return modelAndView;
    }



    /**
     * @Description: 显示编辑页面
     * @Param:
     * @return:
     **/
    @RequestMapping("/edit")
    public ModelAndView editOptionView(){
        ModelAndView modelAndView = new ModelAndView("Admin/Options/index");
        Options option = optionsService.getOptions();

        modelAndView.addObject("option",option);
        return modelAndView;
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
