<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>get javaBean value</title>
</head>
<body>

	<jsp:useBean id="user" class="javabean_test.UserBean" scope="session"></jsp:useBean>
	<p align="center">
		用户：<jsp:getProperty property="username" name="user"/><br>
		最喜欢的水果是：<jsp:getProperty property="friut" name="user"/>
	</p>
</body>
</html>