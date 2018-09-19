<%@page import="com.chen.bean.Books"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function deletebooks(id){
	if(confirm("确定删除？？？")){
		location.href="BookServlet.do?action=delete&bid="+id;
	}
}
function update(id){
	location.href="BookServlet.do?action=update&bid="+id;
}
</script>
<style type="text/css">
table td{
	border: 1px solid black;
}
table {
 width: 50%;
 text-align: center;
 font-family: 微软雅黑;
 }
 tr:hover{
   background-color: yellow;
}
</style>
</head>
<center>
<body>
<table cellspacing="0">
<tr>
	<th>编号</th>
	<th>书名</th>
	<th>作者</th>
	<th>价格</th>
	<th>出版日期</th>
	<th>图片</th>
	<th>上架</th>
	<th>操作</th>
</tr>
<%
	List<Books> bks = (List<Books>)request.getAttribute("booklist");
	if(bks!=null){
		for(Books b:bks){
%>
			<tr>
				<td><%=b.getBid() %></td>
				<td><%=b.getBname() %></td>
				<td><%=b.getBauthor() %></td>
				<td><%=b.getBprice() %></td>
				<td><%=b.getBdate() %></td>
				<td><%=b.getBimage() %></td>
				<td><%=b.getBisonline() %></td>
				<td><a href="#" onclick="deletebooks(<%=b.getBid() %>);">删除</a>
					<a href="#" onclick="update(<%=b.getBid() %>)">修改</a>
				</td>
			</tr>
<%
		}
	}
%>
</table>
<a href="AddBook.jsp">添加书籍</a>
</body>
</center>
</html>