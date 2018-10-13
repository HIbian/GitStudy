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
<style type="text/css">
body{
	overflow:hidden;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
	    <div class="container-fluid">
		    <div class="navbar-header">
		        <a class="navbar-brand" href="#">HRManageSystem</a>
		    </div>
		    <div>
		        <p class="navbar-text">当前用户：${loginUser.userName }</p>
		    </div>
		    <div>
		        <p class="navbar-text">
		        	<a href="UserServlet.do?action=logout" target="_parent">注销用户</a>
				</p>
		    </div>
	    </div>
	</nav>
</body>
</html>