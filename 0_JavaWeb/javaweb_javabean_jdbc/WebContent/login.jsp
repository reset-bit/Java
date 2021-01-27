<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<style>
		*{padding: 0;margin: 0;letter-spacing: 5px;font-family: 'Console';}
		body{height: 100%;}
		.container{
			height: 500px;
			width: 400px;
			border-radius: 10px;
			border: 7px solid rgb(134, 137, 177);
			position: relative;
			left: 50%;
			top: 20%;
			transform: translate(-50%, 10%);
		}
		.header{
			height: 150px;
			width: 100%;
			text-align: center;
			font-style: bold;
			font-size: 50px;
			color:rgb(134, 137, 177);
			position: relative;
			top: 100px;
		}
		.body{
			height: 250px;
			width: 100%;
			position: relative;
			top: 40px;
			left: 30px;
		}
		body .input-item{height: 150px;}
		body .input-item .item{
			display: block;
			height: 40px;
			width: 270px;
			border: 0;
			border-bottom: 1px solid rgb(46, 47, 66);
			margin-top: 0;
			margin-left: 40px;
			-webkit-box-shadow: 0 0 0px 1000px #fff inset
		}
		body .input-item .item::placeholder{
			font-style: bold;
			font-size: 15px;
			text-transform: uppercase;
		}
		body .input-item .draw{
			height: 10px;
			width: 5px;
			margin-top: 40px;
			margin-left: 15px;
			background-color: rgb(134, 137, 177);
		}
		body .submit{
			position: relative;
			margin-left: 70px;
			margin-top: 20px;
		}
	</style>
	<script>
		function check(){
			if(document.getElementById("username").value == ''){
				alert("Please enter the username!");
				return flase;
			}
			if(document.getElementById("password").value == ''){
				alert("Please enter the password!");
				return flase;
			}
			return ture;
		}
	</script>
</head>
<body>
	<div class="container">
		<form method="post" action="/jsp_servlet_javabean_jdbc/servlet" onsubmit="return check()">
			<div class="header">Login</div>
			<div class="body">
				<div class="input-item">
					<div class="draw"></div>
					<input type="text" name="username" placeholder="username" class="item">
					<div class="draw"></div>
					<input type="password" name="password" placeholder="password" class="item">
				</div>
				<div class="submit"><input type="submit" value="Submit" style="width: 200px;height: 30px;"></div>
			</div>
		</form>
	</div>
</body>
</html>