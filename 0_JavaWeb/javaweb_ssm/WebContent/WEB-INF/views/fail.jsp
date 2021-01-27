<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<style>
		td{text-align:center;}
	</style>
	<script>
		function check(){
			if(document.getElementById("username").value == '' | document.getElementById("password").value == ''){
				alert("信息不完整，请检查后重新输入！");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<form action="user.do" method="post" onsubmit="return check()">
		<table align="center">
			<tr>
				<td>编 号：</td>
				<td><input type="number" name="id" id="id"></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" id="username"></td>
			</tr>
			<tr>
				<td>密 码：</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td><input type="submit" value="确定"></td>
				<td><input type="reset" value="清除"></td>
			</tr>
		</table>
	</form>

	<%
		String errorInfo = (String)session.getAttribute("error");
		if(errorInfo != null) {
	%>
	<script>
		alert("<%=errorInfo%>");
		window.location='login.jsp';
	</script>
	<%
		}
	%>
</body>
</html>