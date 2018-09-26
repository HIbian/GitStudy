<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="UserServlet.do?action=updateUser" method="post">
	账号：<input type="text/html" name="userLoginName">
	名字：<input type="text/html" name="userName">
	密码：<input type="text/html" name="userPwd">
	<input type="text/html" value="注册">
</form>
</body>
</html>