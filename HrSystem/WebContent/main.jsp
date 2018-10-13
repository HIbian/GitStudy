<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<frameset rows="50,*" border="1px">
	<frame src="top.jsp" scrolling="yes" noresize="noresize" />
	<frameset cols="200,*">
		<frame src="left.jsp" scrolling="yes" noresize="noresize" />
		<frame src="right.jsp" scrolling="yes" name="right" />
	</frameset>
</frameset>
</html>