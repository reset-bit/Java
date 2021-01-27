<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>search</title>
	<style>
		td{text-align:center;}
	</style>
</head>
<body>
		<table align="center" border="1" cellspacing="0" width="450px">
			<tr>
				<td>id</td><td>username</td><td>mobile</td><td>deptid</td>
			</tr>
			<c:forEach items="${users}" var="u">
			<tr>
				<td>${u.id}</td>
				<td>${u.username}</td>
				<td>${u.mobile}</td>
				<td>${u.deptid}</td>
			</tr>
			</c:forEach>
			<tr><td colspan="4"><input type="button" value="return" onclick="window.location.href='/javaweb_examtest/userlist'"></td></tr>
		</table>
</body>
</html>