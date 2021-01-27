<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>welcome</title>
</head>
<body>
<h1>欢迎你，
<%
	String username = (String)session.getAttribute("username");
	out.println(username);
%>
</h1>
</body>
</html>