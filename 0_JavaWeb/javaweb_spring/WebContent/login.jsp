<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<script>
		function check(){
			if(document.getElementById("username").value == '' | document.getElementById("password").value == ''){
				alert("The information input is incomplete, please check and re-input!");
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<form action="Hello" method="post" onsubmit="return check()">
		username:<input type="text" name="username" id="username"><br>
		password:<input type="password" name="password" id="password"><br>
		<input type="submit" value="submit">
	</form>
</body>
</html>