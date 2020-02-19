package com.teoan.blog.controller.admin;

import com.teoan.blog.entity.User;
import com.teoan.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Teoan
 * @description
 * @date 2020/2/17 16:44
 */
@Controller
@RequestMapping("/admin/user")
public class BackUserController {

    @Autowired
    private UserService userService;


    /**
     * @Description: 用户列表显示
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "")
    public String userList(Model model)  {
        List<User> userList = userService.listUser();

        model.addAttribute("userList",userList);

        return "Admin/User/index";

    }


    /**
     * @Description:  添加用户页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/insert")
    public String insertUserView()  {
        return "Admin/User/insert";
    }


    /**
     * @Description: 检查用户名是否存在
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/checkUserName",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(HttpServletRequest request)  {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "用户名已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }


    /**
     * @Description: 检查邮箱是否存在
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/checkUserEmail",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserEmail(HttpServletRequest request)  {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
        if(user!=null) {
            if(user.getUserId()!=id) {
                map.put("code", 1);
                map.put("msg", "电子邮箱已存在！");
            }
        } else {
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }


    /**
     * @Description:  删除用户
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id)  {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }


    /**
     * @Description: 编辑页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("/edit/{id}")
    public String editUserView(@PathVariable("id") Integer id,Model model)  {
        User user =  userService.getUserById(id);
        model.addAttribute("user",user);
        return "Admin/User/edit";
    }

    /**
     * @Description:  编辑用户提交
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editUserSubmit(User user)  {
        userService.updateUser(user);
        return "redirect:/admin/user";
    }


    /**
     * @Description: 基本信息页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/profile")
    public String userProfileView(HttpSession session,Model model)  {

        User sessionUser = (User) session.getAttribute("user");
        User user =  userService.getUserById(sessionUser.getUserId());
        model.addAttribute("user",user);
        return "Admin/User/profile";
    }

}
