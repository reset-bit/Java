<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" import="java.util.*, cn.edu.sdust.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>users</title>
	<script>
		function check(){
			if(document.getElementById("data").value==="Enter here"){
				alert("Incomplete information in search, please re-enter after checking!");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<form method="post" action="#">
	<table align="center" border="1" cellspacing="0" width="450px">
		<tr>
			<td colspan="5" height="40px">
				<select name="selection">
					<option>tutorid</option>
					<option>tutorname</option>
					<option>years</option>
					<option>tutorinformation</option>
				</select>
				<input type="text" name="data" id="data" value="Enter here">
				<input type="submit" value="search" onsubmit="return check()">
			</td>
			<td colspan="1"><a href="add">add</a></td>
		</tr>
		<tr>
			<td>tutorid</td><td>tutorname</td><td>years</td><td>tutorinformation</td><td colspan="2">option</td>
		</tr>
		
<%-- 		<%  --%>
<!--  			List<User> users = (List<User>)request.getAttribute("users");  -->
<!--  			out.print(users.get(0).getId()); -->
<%-- 		%> --%>
		
		<!-- items要被循环的信息；var当前循环的变量名称 -->
		<c:forEach items="${tutors}" var="t">
		<tr>
			<td>${t.tutorid}</td>
			<td>${t.tutorname}</td>
			<td>${t.years}</td>
			<td>${t.tutorinformation}</td>
			<td><a href="edit?id=${t.tutorid}">edit</a></td>
			<td><a href="delete?id=${t.tutorid}" onclick="return confirm('Are you sure to delete this user?')">delete</a></td>
		</tr>
		</c:forEach>
	</table>
	</form>
</body>
</html>