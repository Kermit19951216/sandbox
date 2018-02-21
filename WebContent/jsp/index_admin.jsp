<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理员主界面</title>
		<meta name="keywords" content="sandplay, 内容检索, 沙盘游戏, 心理分析," />
	       <meta name="author" content="xiaoChuanZhou" />
	       <link rel="stylesheet" type="text/css" href="css/index.css" />
	       <script src="public/js/jquery-3.2.1.js"></script>
	       <script src="js/index_admin.js"></script>
	</head>
	<body>
		<div id="header">
            <div class="welcome-wrap" style="display: block;">
                <div class="welcome-wrap" style="display: block;">
                		<span class="welcome">欢迎你：${admin.name},<a href="admin-Logout.action">登出</a></span>
                </div>
            </div>
        </div>
        <div id="main">
        		<!-- 加载用户之前保存过的图片 -->
            <c:forEach items="${UserItem}" var="item">
				<div class="active user-item" style="left: ${item.xloc}px; top: ${item.yloc}px;" name="${item.name}"><img src="img/sandbox/${item.picname}" /><i class="cancel-icon"></i></div>
			</c:forEach>	
			<div class="nav-bar">
				<!-- 加载用户表 -->
				<!-- <div class="user-list">
					<span class="user-name" title="hahahahahahahahhahahahh">hahahahahahahahhahahahh</span>
					<a href="" class="is-confirm">确认/注销</a>
					<a href="" class="look">查看</a>
				</div> -->
				<c:forEach items="${users}" var="item">
					<div class="user-list">
						<span class="user-name">${item.name}</span>
						<c:choose>
	                			<c:when test="${item.isConfirm == 0}">
	                				<a href="#" class="is-confirm">确认</a>
	                			</c:when>
	                			<c:otherwise>
	                				<a href="#" class="is-lagout">注销</a>
	                			</c:otherwise>
	            			</c:choose>
						<a href="#" class="look">查看</a>
					</div>
				</c:forEach>
			</div>
			<div class="bg-image"></div>
		</div>
	</body>
</html>