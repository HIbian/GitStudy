<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>所有用户</title>
</head>
<body>
<h1>${xiaoxi}</h1>
<table border="1" cellspacing="0px" cellpadding="0px">
	<tr>
		<th>ID</th>
  		<th>姓名</th>
  		<th>密码</th>
  		<th>性别</th>
  		<th>家乡</th>
  		<th>备注</th>
  		<th>操作</th>
  	</tr>
  	<c:forEach var="u" items="${users}">
  		<form action="UpdateServlet" method="post">
	  		<tr>
	  			<td><input type="text" value="${u.id}" name="id"/></td>
	  			<td><input type="text" value="${u.name}" name="name"/></td>
	  			<td><input type="text" value="${u.pwd}" name="pwd"/></td>
	  			<td><input type="text" value="${u.sex}" name="sex"/></td>
	  			<td><input type="text" value="${u.home}" name="home"/></td>
	  			<td><input type="text" value="${u.info}" name="info"/></td>
	  			<td><input type="submit" value="更改" name="change"/>
	  			<a href="DeleteServlet?id=${u.id}" > 删除 </a>
	  			</td>
	  		</tr>
  		</form>
  	</c:forEach>
</table>
</body>
</html>