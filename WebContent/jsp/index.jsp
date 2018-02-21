<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- <script>var name = "${user.name}";
		if(!name) {
			console.log("name is:" + name);
		}
</script> -->
<html>
    <head>
        <meta charset="utf-8" />
        <title>沙盘游戏心理分析系统</title>
        <meta name="description" content="基于内容检索的沙盘游戏心理分析系统" />
        <meta name="keywords" content="sandplay, 内容检索, 沙盘游戏, 心理分析," />
        <meta name="author" content="xiaoChuanZhou" />
        <link rel="stylesheet" type="text/css" href="css/index.css" />
        <script src="public/js/jquery-3.2.1.js"></script>
        <script src="js/index.js"></script>
    </head>
    <body>
        <div id="header">
            <c:choose>
                <c:when test="${user.name == null}">
                    <div class="header-link" style="display: block;">
                        <a href="register.action">注册</a>
                        <a href="login.action">登录</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="welcome-wrap" style="display: block;">
                        <span class="welcome">欢迎你：${user.name},<a href="user-Logout.action">登出</a></span>
                    </div>
                </c:otherwise>
            </c:choose>
            <form class="header-right" id="form">
                 <input name="jsonstr" type="hidden" value="" />
                 <input id="saveData" type="button" value="保存" />
                 <input id="analyse" type="button" value="分析" />
            </form>
        </div>
        <div id="main">
        	<!-- 加载用户之前保存过的图片 -->
            <c:forEach items="${user.items}" var="item">
				<div class="active user-item" style="left: ${item.xloc}px; top: ${item.yloc}px;" name="${item.name}"><img src="img/sandbox/${item.picname}" /><i class="cancel-icon"></i></div>
			</c:forEach>
            <div class="menu-bar">
                <!-- 加载菜单栏图片 -->
			    <c:forEach items="${MenuItem}" var="item">
                    <div class="menu-item" name="${item.name}"><img src="img/sandbox/${item.picname}" /><i class="cancel-icon"></i></div>
                </c:forEach>
            </div>
            <div class="bg-image"></div>
        </div>
        <!-- 分析的结果返回给客户端 -->
        <div class="mask-layer"></div>
        <div class="analysis-dialog">
        		<div class="dialog-header">分析结果</div>
        		<div class="analysis-result"></div>
        		<div class="dialog-bottom">
        			<input class="confirm-btn" type="button" value="确定" ／>
        		</div>
        </div>
    </body>
</html>