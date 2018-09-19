<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>注册</h2>
<hr>
<form action="UserServlet.do?action=register" method="post">
用户名：<input type="text" name="username" ><br/>
密码：<input type="password" name="password" ><br/>
性别：<input type="radio" name="sex" value="1" checked="checked">男
	  <input type="radio" name="sex" value="2">女<br/>
生日：<input type="text" name="birthday" ><br/>
email：<input type="text" name="email" ><br/>
<input type="submit" value="提交">&nbsp;
<input type="reset" value="重置">
</form>
</body>
</html>