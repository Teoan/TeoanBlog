package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.Menu;
import com.teoan.blog.enums.MenuLevel;
import com.teoan.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2020/2/16 15:41
 */
@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {

    @Autowired
    private MenuService menuService;;

    /**
     * @Description: 后台菜单页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("")
    public String menuList(Model model){
        List<Menu> menuList = menuService.listMenu();
        model.addAttribute("menuList",menuList);
        return "Admin/Menu/index";
    }


    /**
     * @Description: 添加菜单提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertMenuSubmit(Menu menu){
        if (menu.getMenuOrder()==null){
            menu.setMenuOrder(MenuLevel.TOP_MENU.getValue());
        }
        menuService.insertMenu(menu);
        return "redirect:/admin/menu";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMenu(@PathVariable("id") Integer id){
        menuService.deleteMenu(id);
        return "redirect:/admin/menu";
    }


    /**
     * @Description: 编辑菜单页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("/edit/{id}")
    public ModelAndView editMenuView(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView("Admin/Menu/edit");

        Menu menu =  menuService.getMenuById(id);
        modelAndView.addObject("menu",menu);

        List<Menu> menuList = menuService.listMenu();
        modelAndView.addObject("menuList",menuList);

        return modelAndView;
    }


    /**
     * @Description: 编辑菜单提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editMenuSubmit(Menu menu){
        menuService.updateMenu(menu);
        return "redirect:/admin/menu";
    }


}
