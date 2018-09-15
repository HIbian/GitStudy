<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>注册用户</title>
</head>
<body>
	<form action="ZhuceServlet" method="POST">
		用户名：<input type="text" name="name"><br/>
		密码：<input type="text" name="pwd"><br/>
		性别：<input type="radio" name="sex" value="男">男&nbsp;
			 <input type="radio" name="sex" value="女">女<br/>
		家乡：<select name="home">
				<option value="上海">上海</option>
				<option value="成都">成都</option>
				<option value="北京">北京</option>
				<option value="杭州">杭州</option>
		</select><br />
		信息：
		<textarea name="info" rows="5" cols="30"></textarea>
		<input type="submit" value="提交">
	</form>
</body>
</html>