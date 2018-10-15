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
$(function() {
	if(${message!=null}){
		$(".alert").removeClass("hidden");
		$(".alert").fadeOut(5000);
	}
})
function register() {
	location.href="UserRegister.jsp";
	//${pageContent.request.contextPath}/UserRegister.jsp
}
function getCode() {
	var img = document.getElementById("code");
	img.src = "CodeServlet.do?"+Math.random();
}
</script>
<title>Insert title here</title>
</head>
<body>
<div class="alert alert-warning hidden" >
			<a href="#" class="close" data-dismiss="alert">
				&times;
			</a>
			<strong>登陆失败！</strong>${message }
</div>
<div id="content">
<form action="UserServlet.do?action=login" method="post" role="form" class="form-horizontal">
	<div class="form-group">
		<label for="username">用户名：</label>
		<input type="text" name="username" class="form-control" placeholder="请输入用户名" id="username">
	</div>
	<div class="form-group">
		<label for="password">密码：</label>
		<input type="password" name="password" class="form-control" placeholder="请输入密码" id="password">
	</div>
	<div class="form-group">
		<img src="CodeServlet.do" id="code"><a href="#" onclick="getCode()">看不清？点击更新验证码</a>
	</div>
	<div class="form-group">
		<label for="code">验证码：</label>
		<input type="text" name="code" class="form-control" placeholder="请输入验证码" id="code">
	</div>
	<div class="form-group">
		<input type="submit" value="登陆" class="btn btn-success">
		<input type="button" value="注册" onclick="register()" class="btn btn-default">
	</div>

</form>
</div>
</body>
</html>