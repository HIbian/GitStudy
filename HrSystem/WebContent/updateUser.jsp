<%@page import="com.hr.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% User u = (User)request.getAttribute("user");%>
<table>
<form action="UserServlet.do?action=update&userId=<%=u.getUserId() %>" method="post">
	<tr>
		<td>账号：</td>
		<td><input type="text" name="userLoginName" value="<%=u.getUserLoginName()%>"></td>
	</tr>
	<tr>
		<td>名字：</td>
		<td><input type="text" name="userName" value="<%=u.getUserName()%>"></td>
	</tr>
	<tr>
		<td>密码：</td>
		<td><input type="text" name="userPwd" value="<%=u.getUserPwd()%>"></td>
	</tr>
	<tr>
		<td>启用状态：</td>
		<td><input type="text" name="state" value="<%=u.getState()%>"></td>
	</tr>
	<tr>
		<td>创建时间：</td>
		<td><input type="text" name="createTime" value="<%=u.getCreateTime()%>"></td>
	</tr>
	
	<tr>
		<td><input type="submit" value="确认修改"></td>
	</tr>
</form>
</table>
</body>
</html>