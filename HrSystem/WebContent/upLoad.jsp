<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="DownloadServlet.do?action=upLoad" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<td>文件名称：</td><td><input type="text" name="downloadTitle"></td>
	</tr>
	<tr>
		<td>文件描述：</td><td><input type="text" name="downloadDes"></td>
	</tr>
	<tr>
		<td>上传文件：</td><td><input type="file" name="file"></td>
	</tr>
	<tr>
		<td><input type="submit" value="上传文件"></td>
	</tr>
</table>
</form>
</body>
</html>