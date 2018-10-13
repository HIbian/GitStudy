<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	if(${message!=null}){
		$(".alert").removeClass("hidden");
		$(".alert").fadeOut(3000);
	}
});
function huan(){
	var img = document.getElementById("code");
	img.src="CodeServlet.do?"+Math.random();
}
</script>
<style type="text/css">
#content{
	width: 20%;
	margin: auto;
}
</style>
</head>
<body>
<div class="alert alert-warning hidden">
			<a href="#" class="close" data-dismiss="alert">
				&times;
			</a>
			<strong>警告！</strong>${message }
</div>
<div id="content">
	<form action="UserServlet.do?action=login" method="post" role="form">
		<!-- <div class="form-group">
			${message}
		</div> -->
		<div class="form-group">
			<label >账号：</label>
			<input type="text" name="userLoginName" class="form-control">
		</div>
		<div class="form-group">
			<label >密码：</label>
			<input type="text" name="userPwd" class="form-control">
		</div>
		<div class="form-group">
			<img src="CodeServlet.do" id="code">
			<a href="#" onclick="huan()">看不清?换一张</a>
		</div>
		<div class="form-group">
			<label >验证码：</label>
			<input type="text" name="userCode" class="form-control">
		</div>
		<div class="form-group">
			<input type="submit" value="登陆" class="btn btn-success">
		</div>
	</form>
</div>
<!--<form action="UserServlet.do?action=login" method="post">
<table>
	<tr>
		<td>${message}</td>
	</tr>
	<tr>
		<td>账号：<input type="text" name="userLoginName"></td>
	</tr>
	<tr>
		<td>密码：<input type="text" name="userPwd"></td>
	</tr>
	<tr>
		<td><img src="CodeServlet.do" id="code"><a href="#" onclick="huan()"">看不清?换一张</a></td>
	</tr>
	<tr>
		<td>验证码：<input type="text" name="userCode"></td>
	</tr>
	<tr>
		<td><input type="submit" value="登陆"></td>
	</tr>
</table>
</form>-->

</body>
</html>