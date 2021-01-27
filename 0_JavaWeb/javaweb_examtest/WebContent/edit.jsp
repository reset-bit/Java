<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" import="java.util.*, cn.edu.sdust.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>edit</title>
	<style>
		td{text-align:center;}
	</style>
	<script>
		function check(){
			if(document.getElementById("username").value == '' | 
					document.getElementById("mobile").value == '' |
					document.getElementById("deptid").value == '' |
					){
				alert("Incomplete information, please re-enter after checking!");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<form action="save" method="post" onsubmit="return check()">		
		<table align="center">
			<tr>
				<td><input type="hidden" name="id" value=${user.id}></td>
			</tr>
			<tr>
				<td>username:</td>
				<td><input type="text" name="username" id="username" value=${user.username}></td>
			</tr>
			<tr>
				<td>mobile:</td>
				<td><input type="text" name="mobile" id="mobile" value=${user.mobile}></td>
			</tr>
			<tr>
				<td>deptid:</td>
				<td><input type="number" name="deptid"  id="deptid" value=${user.deptid}></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
				<td><input type="reset" value="Reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>