<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8]>
<html xmlns="http://www.w3.org/1999/xhtml" class="ie8" lang="zh-CN">
<![endif]-->
<!--[if !(IE 8) ]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<!--<![endif]-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${options.optionSiteTitle} &lsaquo; 登录</title>
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
    <link rel="shortcut icon" href="/img/logo.png">
    <link rel="stylesheet" href="/plugin/layui/css/layui.css">
    <style type="text/css">
        body{
            font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
            background: url(/img/loginBg.jpg);
            width:100%;
            height:100%;
        }

        #backtoblog a, #nav a {
            color: #fff !important;
        }

        #login{
            text-align: center;
            vertical-align: center;
            background-color: #d2d2d2;
            background-color:rgba(255, 255, 255, 0.6);
            width: 400px;
            height: 300px;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;

        }
        /*#inputDiv{*/
        /*    width: 260px;*/
        /*    height: 100px;*/
        /*    margin: auto;*/
        /*    text-align: center;*/
        /*}*/
        #loginForm{
            margin: auto;
            width: 260px;
            height: 150px;
            text-align: center;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
        }
    </style>
    <meta name='robots' content='noindex,follow' />
    <meta name="viewport" content="width=device-width" />
</head>
<body>
<div id="login" >
    <%
        String username = "";
        String password = "";
        //获取当前站点的所有Cookie
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
            if ("username".equals(cookies[i].getName())) {
                username = cookies[i].getValue();
            } else if ("password".equals(cookies[i].getName())) {
                password = cookies[i].getValue();
            }
        }
    %>
    <form name="loginForm" id="loginForm"  class="layui-form" method="post" >
        <div id="inputDiv">
            <div class="layui-form-item">
                <input type="text" name="username" id="user_login" class="layui-input" placeholder="请输入账号或者邮箱" value="<%=username%>"  required/>
            </div>
            <div class="layui-form-item">
                <input type="password" name="password" id="user_pass" class="layui-input" placeholder="请输入密码" value="<%=password%>"  required/>
            </div>
        </div>
        <div class="layui-form-item">
            <input name="rememberme" type="checkbox" lay-skin="primary" id="rememberme" value="1" title="记住密码" checked />
            <input type="button" name="wp-submit" id="submit-btn" class="layui-btn" value="登录" />
        </div>
        <div class="layui-form-item">
            <a href="/">&larr; 返回到风吟博客</a>
        </div>
    </form>



    <script type="text/javascript">
        function wp_attempt_focus(){
            setTimeout( function(){ try{
                d = document.getElementById('user_login');
                d.focus();
                d.select();
            } catch(e){}
            }, 200);
        }

        wp_attempt_focus();
        if(typeof wpOnload=='function')wpOnload();
    </script>

</div>





<script src="/js/jquery.min.js"></script>
<script src="/plugin/layui/layui.all.js"></script>
<script type="text/javascript">


    <%--登录验证--%>
    $("#submit-btn").click(function () {
        var user = $("#user_login").val();
        var password = $("#user_pass").val();
        if(user=="") {
            alert("用户名不可为空!");
        } else if(password==""){
            alert("密码不可为空!");
        } else {
            $.ajax({
                async: false,//同步，待请求完毕后再执行后面的代码
                type: "POST",
                url: '/loginVerify',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: $("#loginForm").serialize(),
                dataType: "json",
                success: function (data) {
                    if(data.code==1) {
                        alert(data.msg);
                    } else {
                        window.location.href="/admin";

                    }
                },
                error: function () {
                    alert("数据获取失败")
                }
            })
        }
    })

</script>
</body>
</html>

