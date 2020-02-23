package com.teoan.blog.controller.admin;

import cn.hutool.crypto.Mode;
import com.teoan.blog.dto.JsonResult;
import com.teoan.blog.entity.Article;
import com.teoan.blog.entity.Comment;
import com.teoan.blog.entity.User;
import com.teoan.blog.service.ArticleService;
import com.teoan.blog.service.CommentService;
import com.teoan.blog.service.UserService;
import com.teoan.blog.util.MyUtils;
import org.apache.cxf.transport.http.HTTPSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Teoan
 * @description 后台管理员Controller类
 * @date 2020/2/12 14:44
 */
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;


    /**
     * @Description: 后台首页
     * @Param:
     * @return:
     **/
    @RequestMapping("/admin")
    public String index(Model model){

        //文章列表
        List<Article> articles = articleService.listRandomArticle(5);
        model.addAttribute("articleList",articles);

        //评论列表
        List<Comment> comments = commentService.listRecentComment(5);
        model.addAttribute("commentList",comments);
        return "Admin/index";
    }

    /**
     * @Description: 登录页面显示
     * @Param:
     * @return:
     **/
    @RequestMapping("/login")
    public String loginPage() {
        return "Admin/login";
    }



    /**
     * @Description: 登录验证
     * @Param:
     * @return:
     **/
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> loginVerify(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");

        User user = userService.getUserByNameOrEmail(username);

        //登录校验
        if (user==null){
            map = JsonResult.fail("用户名无效！");
        }else if (!user.getUserPass().equals(password)){
            map = JsonResult.fail("密码无效！");
        }else{
            //登录成功
            map = JsonResult.ok("");

            //添加到Session域中
            request.getSession().setAttribute("user",user);

            //添加cookie
            if (rememberme!=null){
                //创建用户名密码两个Cookie对象
                Cookie nameCookie = new Cookie("username",username);
                Cookie pwdCookie = new Cookie("password",password);

                //设置有效期3天
                nameCookie.setMaxAge(60*60*24*3);
                pwdCookie.setMaxAge(60*60*24*3);

                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            //更新用户信息
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(MyUtils.getIpAddr(request));
            userService.updateUser(user);
        }
        return map;
    }

    /**
     * @Description: 退出登录
     * @Param:
     * @return:
     **/
    @RequestMapping("/admin/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }

}
