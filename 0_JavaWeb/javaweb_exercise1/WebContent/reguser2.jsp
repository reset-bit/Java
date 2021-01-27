<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>reguser2</title>
</head>
<body>
您的注册信息为：<br>
姓名：<%=session.getAttribute("username")%><br>
年龄：
<%
	Cookie[] cookies = request.getCookies();
	for(int i = 0; i < cookies.length; ++i){
		if("age".equals(cookies[i].getName())){
			out.print(cookies[i].getValue());
		}
	}
%>
<br>
学校：<%=application.getAttribute("school")%>
</body>
</html>