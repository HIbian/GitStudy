<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${xiaoxi}
<table border="1px" cellspacing="0px" cellpadding="0px">
	<tr><th>ID</th><th>USERNAME</th><th>USERPASS</th><th>DO</th></tr>
	<c:forEach var="u" items="${users}">
		<form action="delete.do" method="post">
			<tr>
				<td><input type="text" name="id" value="${u.id}"></td>
				<td>${u.username}</td>
				<td>${u.userpass}</td>
				<td>
					<input type="submit" value="删除">
				</td>
			</tr>
		</form>
	</c:forEach>
</table>
</body>
</html>