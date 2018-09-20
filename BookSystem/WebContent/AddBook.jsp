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
<h2>添加书籍</h2>
<hr>
<form action="BookServlet.do?action=addbook" method="post" enctype="multipart/form-data">
书名：<input type="text" name="bname"><br/>
作者：<input type="text" name="bauthor"><br/>
价格：<input type="text" name="bprice"><br/>
出版日期：<input type="text" name="bdate" onfocus="WdatePicker({isShowClear:true,readOnly:true,skin:'blue'})"><br/>
图片：<input type="file" name="bimage"><br/>
是否上架：<input type="radio" name="bisonline" value="1">是<input type="radio" name="bisonline" value="0">否<br/>
<input type="submit" value="提交">
</form>
</body>
</html>