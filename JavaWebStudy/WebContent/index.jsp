<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ServletTest</title>
</head>
<body>
	<form action="DengluServlet" method="POST">
		账号：<input type="text" name="username"></br>
		密码：<input type="password" name="password"></br>
		<input type="submit" value="提交"> 
	</form>
	
	<form action="Zhuce.jsp">
	 	<input type="submit"value="新用户注册">
    </form>

</body>
</html>