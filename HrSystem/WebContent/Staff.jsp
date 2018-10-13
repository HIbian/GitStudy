<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

<!-- 引入Bootstrap核心样式文件 -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- 引入jQuery核心js文件 -->
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
<!-- 引入BootStrap核心js文件 -->
<script src="js/bootstrap.min.js"></script>

<title>Insert title here</title>
<script type="text/javascript">
function update(staffId){
	location.href="StaffServlet.do?action=update&staffId="+staffId;
}
$(function(){
	$("#selectAll").click(function(){
		$(".ck").prop("checked",this.checked);
	});
	$("#delete").click(function(){
		var delstr ="";
		$("#staffData").find(".ck:checked").each(function(){
			delstr+=$(this).val()+",";
		});
		location.href="StaffServlet.do?action=deleteStaff&delstr="+delstr;
	});
})
</script>
</head>
<body>
<form action="StaffServlet.do?action=query" method="POST" role="form" class="form-inline">
	<div class="form-group">
		<label for="staffId">编号：</label>
		<input type="text" class="form-control" name="staffId" placeholder="请输入编号" value="${phs.parms.staffId==-1?'':phs.parms.staffId }">
	</div>
	<div class="form-group">
		<label for="staffName">名称：</label>
		<input type="text" class="form-control" name="staffName" placeholder="请输入名称" value="${phs.parms.staffName }">
	</div>
	<input type="submit" class="btn btn-default" value="搜索">
	<input type="button" class="btn btn-danger" id="delete" value="删除">
</form>
<!-- <tr>
		<td>编号：</td>
		<td><input type="text" name="staffId" value="${phs.parms.staffId==-1?'':phs.parms.staffId }"></td>
	</tr>
	<tr>
		<td>名称：</td>
		<td><input type="text" name="staffName" value="${phs.parms.staffName }"></td>
	</tr>
 -->



<table id="staffData" class="table table-hover table-bordered table-striped">
	<tr class="warning">
		<td><input type="checkbox" id="selectAll"></td>
		<td>职位编号</td>
		<td>职位名称</td>
		<td>职位描述</td>
		<td>操作</td>
	</tr>
	<c:forEach var="s" items="${phs.listData }">
	<tr>
		<td><input type="checkbox" class="ck" value="${s.staffId }"></td>
		<td>${s.staffId }</td>
		<td>${s.staffName }</td>
		<td>${s.staffDes }</td>
		<td><input type="button" class="btn btn-default" value="更改" onclick="update(${s.staffId})"></td>
	</tr>
	</c:forEach>
</table>
</body>
</html>