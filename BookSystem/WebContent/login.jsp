<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function huan(){
		var img = document.getElementById("code");
		img.src="CodeServlet.do?"+Math.random();
	}
</script>
</head>
<body>
<% 
	Cookie[] cs = request.getCookies(); 
	String username="";
	String password="";
	if(cs!=null){
		for(Cookie c:cs){
			if("loginfo_username".equals(c.getName())){
				username = c.getValue();
			}
			if("loginfo_password".equals(c.getName())){
				password = c.getValue();
			}
		}
	}
%>
<form action="UserServlet.do?action=login" method="post">
用户名：<input type="text" name="username" value="<%=username%>"><br/>
密码：<input type="password" name="password" value="<%=password%>"><br/>
验证码：<input type="text" name="code" ><br/><img src="CodeServlet.do" id="code"><a href="#" onclick="huan()">看不清？换一张</a><br/>
记住密码<input type="checkbox" name="rember" value="1"> <br/>
<input type="submit" value="登陆"><br/>
没有账号？<a href="register.jsp">点击注册</a>
</form>
</body>
</html>