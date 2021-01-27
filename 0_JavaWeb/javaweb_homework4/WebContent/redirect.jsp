<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>redirect</title>
</head>
<body>
<% String username = request.getParameter("username"); %>
欢迎您，老先生<h1><%out.print(username); %></h1>
</body>
</html>