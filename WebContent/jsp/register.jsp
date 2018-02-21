<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 

<!DOCTYPE html>
<script>
    var flag = "${signinFlag}";
    if(flag == 1){
    	alert("注册失败");
    }
</script>
<html>
    <head>
        <meta charset="utf-8" />
        <title>个人注册</title>
        <link rel="stylesheet" type="text/css" href="css/register.css"  />
        <script src="public/js/jquery-3.2.1.js"></script>
        <script src="js/register.js"></script>
    </head>
    <body>
        <div class="header">
            <class="header-wrap">
                <h3>欢迎注册</h3>
                <div class="have-account">
                    <span>已有账号？</span>
                    <a href="login.action">请登录</a>
                </div>
            </class>
        </div>
        <div class="register-wrap">
            <form action="user-SignIn.action" method="post" >
                <p>
                    <label for="user-name">用户名:</label>
                    <input placeholder="你的账户名和登录名" id="user-name" name="name" autocomplete="off" />
                </p>
                <div class="input-tip">
                    <i class="account-icon"></i>
                    <span id="account-error" class="error"></span>
                </div>
                <p>
                    <label for="login-pwd">设置密码:</label>
                    <input type="password" placeholder="建议至少使用两种字符组合" id="set-pwd" name="password" autocomplete="off"/>
                </p>
                <div class="input-tip">
                    <i class="setPwd-icon"></i>
                    <span id="setPwd-error" class="error"></span>
                </div>
                <p>
                    <label for="rePwd">确认密码:</label>
                    <input type="password" placeholder="请再次输入密码" id="rePwd" autocomplete="off"/>
                </p>
                <div class="input-tip">
                    <i class="rePwd-icon"></i>
                    <span id="rePwd-error" class="error"></span>
                </div>
                <input type="submit" value="立即注册" class="register-btn" />
            </form>
            <iframe name ="mywin" frameborder="0" src='' style="display: none"></iframe> 
        </div>
    </body>
</html>