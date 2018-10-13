<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>更新Staff</h2>
<form action="StaffServlet.do?action=realupdate&staffId=${staff.staffId }" method="post">
	<table>
		<tr>
			<td>职位编号：</td>
			<td><span>${staff.staffId}</span></td>
		</tr>
		<tr>
			<td>职位名称：</td>
			<td><input type="text" name="staffName" value="${staff.staffName }"></td>
		</tr>
		<tr>
			<td>职位描述：</td>
			<td><input type="text" name="staffDes" value="${staff.staffDes }"></td>
		</tr>
		<tr>
			<td><input type="submit" value="确认更改"></td>
		</tr>
	</table>
</form>
</body>
</html>