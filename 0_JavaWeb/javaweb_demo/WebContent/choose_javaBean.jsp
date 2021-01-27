<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>please choose your favorite friut</title>
	<script>
		function check(form){
			var flag1 = false, flag2 = false;
			if(document.getElementByName("username").value != ''){
				flag1 = true;
			}
			for(var i = 0; i < form.elements.length; ++i){
				if(form.elements[i].checked){
					flag2 = true;
				}
			}
			if(!flag1){
				alert("please enter the username!");
			}
			if(!flag2){
				alert("please choose an option!");
			}
			if(flag1 && flag2){
				return true;
			}
			return false;
		}
	</script>
</head>
<body>
	<form action="javaBean" method="get" onsubmit="return check(this)">
		<table align="center" border="1" cellspacing="0">
			<tr><td colspan="2"><h1>What is your favorite friut?</h1></td></tr>
			<tr><td height="40px" align="right">username:</td><td><input type="text" name="username"></td></tr>
			<tr>
				<td height="40px" align="right">friut:</td>
				<td>
					<input type="radio" value="apple" name="friut">apple
					<input type="radio" value="pear" name="friut">pear
					<input type="radio" value="orange" name="friut">orange
					<input type="radio" value="banana" name="friut">banana
					<input type="radio" value="straberry" name="friut">straberry
					<input type="radio" value="peach" name="friut">peach
					<input type="radio" value="other" name="friut">other
				</td>
			</tr>
			<tr><td colspan="2" style="text-align:center;line-height:40px;"><input type="submit" value="submit"></td></tr>
		</table>
	</form>
</body>
</html>