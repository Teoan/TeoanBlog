package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.Category;
import com.teoan.blog.service.ArticleService;
import com.teoan.blog.service.CategoryService;
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
 * @date 2020/2/15 15:08
 */
@Controller
@RequestMapping("/admin/category")
public class BackCategoryController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    /**
     * @Description: 后台分类列表显示
     * @Param:
     * @return:
     **/

    @RequestMapping("")
    public String categoryList(Model model ){
        List<Category> categoryList = categoryService.listCategoryWithCount();
        model.addAttribute("categoryList",categoryList);
        return "Admin/Category/index";
    }


    /**
     * @Description: 添加分类提交
     * @Param:  分类
     * @return:
     **/
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertCategorySubmit(Category category){
        categoryService.insertCategory(category);
        return "redirect:/admin/category";
    }


    /**
     * @Description: 删除文章分类
     * @Param:  分类id
     * @return:
     **/
    @RequestMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id){
        //禁止删除有文章的分类
        int count = articleService.countArticleByCategoryId(id);
        if(count ==0){
            categoryService.deleteCategory(id);
        }
        return "redirect:/admin/category";
    }

    /**
     * @Description: 编辑页面显示
     * @Param:  id
     * @return:
     **/
    @RequestMapping("/edit/{id}")
    public String editCategoryView(@PathVariable("id") Integer id,Model model)  {


        Category category =  categoryService.getCategoryById(id);

        model.addAttribute("category",category);

        List<Category> categoryList = categoryService.listCategoryWithCount();
        model.addAttribute("categoryList",categoryList);

        return "Admin/Category/edit";
    }


    /**
     * @Description: 编辑分类提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCategorySubmit(Category category)  {
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }
}
