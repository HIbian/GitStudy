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
<style type="text/css">
#content{
	width: 30%;
	margin-left: 2%;
}
</style>
</head>
<body>
<div id="content">
	<form action="UserServlet.do?action=addUser" method="post" role="form" class="form-horizontal">
		<div class="form-group">
			<label for="userLoginName">账号：</label><input type="text" name="userLoginName" class="form-control">
		</div>
		<div class="form-group">
			<label>名字：</label><input type="text" name="userName" class="form-control">
		</div>
		<div class="form-group">
			<label>密码：</label><input type="text" name="userPwd" class="form-control">
		</div>
		<div class="form-group">
			<label>重复密码：</label><input type="text" name="rePwd" class="form-control">
		</div>
		<div class="form-group">
			<input type="submit" value="添加" class="btn btn-default">
		</div>
	</form>
</div>
</body>
</html>