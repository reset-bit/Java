<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>error</title>
	<style>
		*{padding: 0;margin: 0;letter-spacing: 3px;font-family: 'Console';}
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
			text-align: center;
			font-style: bold;
			font-size: 20px;
			color:rgb(134, 137, 177);
			position: relative;
			margin-top: 70px;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="header">Error!</div>
		<div class="body">
			Login failed.<br>
			Please check your input information!
		</div>
	</div>
</body>
</html>