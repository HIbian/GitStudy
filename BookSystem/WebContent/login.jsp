<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="UserServlet.do?action=login" method="post">
用户名：<input type="text" name="username"><br/>
密码：<input type="password" name="password"><br/>
验证码：<input type="text" name="code" ><br/><img src="CodeServlet.do"><br/>
<input type="submit" value="登陆"><br/>
没有账号？<a href="register.jsp">点击注册</a>
</form>
</body>
</html>