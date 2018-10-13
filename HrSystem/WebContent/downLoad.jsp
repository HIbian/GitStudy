<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function down(id){
	location.href="DownloadServlet.do?action=download&id="+id;
}
</script>
</head>
<body>
	<table class="table table-hover table-striped table-bordered table-responsive">
		<tr class="warning">
			<td><input type="checkbox"></td>
			<td>文件序号</td>
			<td>文件标题</td>
			<td>创建时间</td>
			<td>上传用户</td>
			<td>文件描述</td>
			<td>文件路径</td>
			<td>操作</td>
		</tr>
		<c:forEach var="d" items="${dls }">
		<tr>
			<td><input type="checkbox"></td>
			<td>${d.downloadId }</td>
			<td>${d.downloadTittle }</td>
			<td>${d.downloadCreateTime }</td>
			<td>${d.userId }</td>
			<td>${d.downloadDes }</td>
			<td>${d.downPath }</td>
			<td><button id="download_btn" onclick="down(${d.downloadId})">下载</button></td>
		</tr>
		</c:forEach>
		
	</table>
	<ul class="pagination">
		<li class="active"><span>[共${totalCount }条数据]</span><li>
	</ul>
</body>
</html>