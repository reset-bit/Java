<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>企客宝客户管理系统-员工登录页面</title>
    <link rel="stylesheet" href="/crmsystem_root/static/css/mylogin.css">
   	<script>
	    function check() {
	        if (document.getElementById("item1").value == '') {
	            alert("请输入用户名！");
	        }
	        if (document.getElementById("item2").value == '') {
	            alert("请输入密码！");
	        }
	        if(document.getElementById("item1").value != '' && document.getElementById("item2").value != ''){
	        	document.getElementById("datas").submit();
	        }
	    }
   	</script>
</head>
<body>
    <div class="container">
        <form method="post" action="/crmsystem_root/sheet/checklogin" id="datas">
            <div class="header">
                <font>企客宝-员工登录</font>
            </div>
            <div class="body">
                <div class="input-item">
                    <input type="text" name="empname" placeholder="请输入用户名" class="item" id="item1">
                    <input type="password" name="emppassword" placeholder="请输入密码" class="item" id="item2">
                </div>
                <div class="submit">
                    <div class="btn btn-blue">
                        <a onclick="check()" class="btn-text" id="go">登录</a>
                    </div>
                </div>
                <div class="msg">
                    忘记密码？<a href="#">点此找回</a>
                </div>
            </div>
        </form>
    </div>
</body>
    
</html>