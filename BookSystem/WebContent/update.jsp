<%@page import="com.chen.bean.Books"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/my97datepicker4.6/WdatePicker.js"></script>
</head>
<body>
<%
Books book = (Books)request.getAttribute("book"); 
%>
<form action="BookServlet.do?action=doupdate&bid=<%=book.getBid()%>" method="post">
书名：<input type="text" name="bname" value="<%=book.getBname() %>"><br/>
作者：<input type="text" name="bauthor" value="<%=book.getBauthor() %>"><br/>
价格：<input type="text" name="bprice" value="<%=book.getBprice() %>"><br/>
出版日期：<input type="text" name="bdate" onfocus="WdatePicker({isShowClear:true,readOnly:true,skin:'blue'})" value="<%=book.getBdate() %>"><br/>
图片：<input type="text" name="bimage" value="<%=book.getBimage() %>"><br/>
<%
if(book.getBisonline()==1){
%>
	是否上架：<input type="radio" name="bisonline" value="1" checked="checked">是<input type="radio" name="bisonline" value="0">否<br/>
<%
}else{
%>
	是否上架：<input type="radio" name="bisonline" value="1">是<input type="radio" name="bisonline" value="0" checked="checked">否<br/>
<%
}
%>
<input type="submit" value="修改">
</form>
</body>
</html>