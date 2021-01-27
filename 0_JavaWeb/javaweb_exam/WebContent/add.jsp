<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>add</title>
	<style>
		td{text-align:center;}
	</style>
	<script>
		function checkIsNull(){
			if(document.getElementById("tutorname").value == "" | 
					document.getElementById("years").value == '' |
					document.getElementById("tutorinformation").value == ""
					){
				//System.out.print("check");
				alert("Incomplete information, please re-enter after checking!");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<form action="save" method="post" onsubmit="javascript:return checkIsNull()">
		<table align="center">
			<tr>
				<td><input type="hidden" name="tutorid" id="tutorid" value="-1" ></td>
			</tr>
			<tr>
				<td>tutorname:</td>
				<td><input type="text" name="tutorname" id="tutorname"></td>
			</tr>
			<tr>
				<td>years:</td>
				<td><input type="number" name="years" id="years"></td>
			</tr>
			<tr>
				<td>tutorinformation:</td>
				<td><input type="text" name="tutorinformation" id="tutorinformation"></td>
			</tr>
			<tr>				
				<td><input type="submit" value="Submit"></td>
				<td><input type="reset" value="Reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>