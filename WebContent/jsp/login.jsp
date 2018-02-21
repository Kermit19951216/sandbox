<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<script>var flag = "${loginFlag}";</script>
<html>
    <head>
        <meta charset="utf-8" />
        <title>沙盘游戏心理分析系统登录界面</title>
        <meta name="author" content="萧传州" />
        <link rel="stylesheet" type="text/css" href="css/login.css" />
        <script src="public/js/jquery-3.2.1.js"></script>
        <script src="js/login.js"></script>
    </head>
    <body>
        <div class="user-login">
            <div class="login-wrap">
                <h1>沙盘游戏心理分析系统登录</h1>
                <div class="login-msg">
                    <p class="error-msg">
                        <span class="error"></span>
                    </p>
                </div>
                <form id="loginForm" action="user-Login.action" method="post">
                    <input class="user-name text" type="text" placeholder="请输入用户名" id="name" name="name" autocomplete="off" />
                    <input class="login-pwd text" type="password" placeholder="请输入密码" id="password" name="password" autocomplete="off"/>
                    <input  class="identify" type="text" placeholder="请输入验证码" />
                    <!-- 验证码 -->
                    <span class="captcha"></span>
                    <p class="prompt">看不清？<i class="exchange">换一张<i></p>
                    <div class="status-wrap">
                        <span><input type="radio" name="status" value="user-Login.action"/>&nbsp;用户</span>
                        <span><input type="radio" name="status" value="admin-Login.action" />&nbsp;管理员</span>
                    </div>
                    <input class="login-btn" type="submit" value="登录" onclick="login()"/>
                </form>
                <div class="login-links">
                    <a href="#" class="forgot-pwd" target="_blank">忘记密码</a>
                    <a href="register.action" class="forgot-pwd" >注册&nbsp;&gt;</a>
                </div>
            </div>
        </div>
    </body>
</html>