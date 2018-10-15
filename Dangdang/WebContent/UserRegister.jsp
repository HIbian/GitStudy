<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<!-- 引入Bootstrap核心样式文件 -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- 引入jQuery核心js文件 -->
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
<!-- 引入BootStrap核心js文件 -->
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
#content{
	width: 30%;
	margin: auto;
}
</style>
<script type="text/javascript">
function isusername() {
	//获取usernam
	var username = document.getElementById("username").value;
	//获取显示对象
	var label_username = document.getElementById("label_username");
	//创建对象
	var xhr = new XMLHttpRequest();
	//设置回掉函数
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var data = xhr.responseText;
			if (data=="true") {
				label_username.style="color:green";
				label_username.innerHTML="用户名：用户名合法";
			}else {
				label_username.style="color:red";
				label_username.innerHTML="用户名：用户名不合法";
			}
		}
	};
	xhr.open("GET","UserServlet.do?action=isusername&username="+username,true);
	xhr.send();
}
function ispassword(){
	//获取password value
	var password = document.getElementById("password").value;
	//获取label_password
	var label_password = document.getElementById("label_password");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if (xhr.readyState==4 && xhr.status==200) {
			var data = xhr.responseText;
			if (data=="true") {
				label_password.style="color:green";
				label_password.innerHTML="密码：密码合法";
			}else {
				label_password.style="color:red";
				label_password.innerHTML="密码：密码不合法";
			}
		}
	};
	xhr.open("GET","UserServlet.do?action=ispassword&password="+password,true);
	xhr.send();
}
function isre() {
	//获取password value
	var password = document.getElementById("password").value;
	//获取re value
	var re = document.getElementById("re").value;
	//获取label_password
	var label_re = document.getElementById("label_re");
	if (password==re) {//密码一样
		label_re.style="color:green";
		label_re.innerHTML="重复密码：两次输入密码一致";
	}else{
		label_re.style="color:red";
		label_re.innerHTML="重复密码：两次输入密码不一致";
	}
}
function isemail() {
	var email = document.getElementById("email").value;
	var label_email = document.getElementById("label_email");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if (xhr.readyState==4 && xhr.status==200) {
			var data = xhr.responseText;
			if (data=="true") {
				label_email.style="color:green";
				label_email.innerHTML="电子邮箱：电子邮箱合法";
				$("#queren").removeClass("hidden");
			}else {
				label_email.style="color:red";
				label_email.innerHTML="电子邮箱：电子邮箱不合法";
			}
		}
	};
	xhr.open("GET","UserServlet.do?action=isemail&email="+email,true);
	xhr.send();
}
</script>
<title>Insert title here</title>
</head>
<body>
<div id="content">
<form action="UserServlet.do?action=register" method="post" role="form" class="form-horizontal">
	<div class="form-group">
		<label for="username" id="label_username">用户名：</label>
		<input type="text" name="username" class="form-control" placeholder="请输入用户名" id="username" onblur="isusername()">
	</div>
	<div class="form-group">
		<label for="password" id="label_password">密码：</label>
		<input type="password" name="password" class="form-control" placeholder="请输入密码" id="password" onblur="ispassword()">
	</div>
	<div class="form-group">
		<label for="re" id="label_re">重复密码：</label>
		<input type="password" name="re" class="form-control" placeholder="请再次输入密码" id="re" onblur="isre()">
	</div>
	<div class="form-group">
		<label for="email" id="label_email">电子邮箱：</label>
		<input type="text" name="email" class="form-control" placeholder="请输入email" id="email" onblur="isemail()">
	</div>
	<div class="form-group">
		<input type="submit" value="确认注册" class="btn btn-default btn hidden" id="queren">
	</div>
</form>
</div>
</body>
</html>