<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
<!--	<ul>
		<li>用户管理</li>
		<li><a href="UserServlet.do?action=index" target="right">查询用户</a></li>
		<li><a href="addUesr.jsp" target="right">添加用户</a></li>
	</ul>
	<ul>
		<li>员工管理</li>
		<li><a href="empServlet.do?action=query" target="right">查询员工</a></li>
		<li><a href="addEmp.jsp" target="right">添加员工</a></li>
	</ul>
	<ul>
		<li>部门管理</li>
		<li><a href="DeptServlet.do?action=query" target="right">查询部门</a></li>
		<li><a href="addDept.jsp" target="right">添加部门</a></li>
	</ul>
	<ul>
		<li>职位管理</li>
		<li><a href="StaffServlet.do?action=query" target="right">查询职位</a></li>
		<li><a href="addStaff.jsp" target="right">添加职位</a></li>
	</ul>
	<ul>
		<li>公告管理</li>
		<li><a href="MessageServlet.do?action=query" target="right">查询公告</a></li>
		<li><a href="addMessage.jsp" target="right">添加公告</a></li>
	</ul>
	<ul>
		<li>下载中心</li>
		<li><a href="DownloadServlet.do?action=query" target="right">下载文档</a></li>
		<li><a href="upLoad.jsp" target="right">上传文档</a></li>
	</ul>
	
	 ----------------------------------------------------------------------- -->
<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapseOne">用户管理</a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body">
                <a href="UserServlet.do?action=index" target="right">查询用户</a>
            </div>
            <div class="panel-body">
                <a href="addUesr.jsp" target="right">添加用户</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapseTwo">员工管理</a></h4>
        </div>
        <div id="collapseTwo" class="panel-collapse collapse">
	        <div class="panel-body">
				<a href="empServlet.do?action=query" target="right">查询员工</a>
	        </div>
	        <div class="panel-body">
				<a href="addEmp.jsp" target="right">添加员工</a>
	        </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapse3">部门管理</a></h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
	        <div class="panel-body">
				<a href="StaffServlet.do?action=query" target="right">查询职位</a>
	        </div>
	        <div class="panel-body">
				<a href="addDept.jsp" target="right">添加部门</a>
	        </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapse4">职位管理</a></h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
	        <div class="panel-body">
				<a href="StaffServlet.do?action=query" target="right">查询职位</a>
	        </div>
	        <div class="panel-body">
				<a href="addStaff.jsp" target="right">添加职位</a>
	        </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapse5">公告管理</a></h4>
        </div>
        <div id="collapse5" class="panel-collapse collapse">
	        <div class="panel-body">
				<a href="MessageServlet.do?action=query" target="right">查询公告</a>
	        </div>
	        <div class="panel-body">
				<a href="addMessage.jsp" target="right">添加公告</a>
	        </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" 
                href="#collapse6">下载中心</a></h4>
        </div>
        <div id="collapse6" class="panel-collapse collapse">
	        <div class="panel-body">
				<a href="DownloadServlet.do?action=query" target="right">下载文档</a>
	        </div>
	        <div class="panel-body">
				<a href="upLoad.jsp" target="right">上传文档</a>
	        </div>
        </div>
    </div>
</div>

</body>
</html>