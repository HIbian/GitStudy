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
window.onload=function(){
	getStaffs();
	getDepts();
}
//Ajax,json运用
function getStaffs(){
	//获得select节点
	var staffs = document.getElementById("staffs");
	//创建对象
	var xhr = new XMLHttpRequest();
	//设置回掉函数
	xhr.onreadystatechange=function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = xhr.responseText;
			//alert(data);
			//JsonString转换为json对象
			var html="";
			var jsonArray=JSON.parse(data.trim());
			html+="<option value='-1'>--请选择--</option>";
			for(var i=0;i<jsonArray.length;i++){
				if(jsonArray[i].staffId==${pg.parms.staffId}){
					html+="<option value="+jsonArray[i].staffId+" selected>"+jsonArray[i].staffName+"</option>";
					continue;
				}
				html+="<option value="+jsonArray[i].staffId+">"+jsonArray[i].staffName+"</option>";
			}
			staffs.innerHTML=html;
		}
	};
	//初始化请求
	xhr.open("GET","${pageContext.request.contextPath}/empServlet.do?action=getStaffs",true);//异步加载
	//发送请求
	xhr.send();
}
function getDepts(){
	//获取部门选择框对象
	var deptName = document.getElementById("deptName");
	//新建xhr对象
	var xhr = new XMLHttpRequest();
	//设置回掉函数
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var data = xhr.responseText;//获取返回的jsonString
			var jarray = JSON.parse(data.trim());//转化为json数组
			var html="";
			html+="<option value='-1'>--请选择--</option>";
			for(var i=0;i<jarray.length;i++){
				if(jarray[i].did==${pg.parms.dId}){
					html+="<option value="+jarray[i].did+" selected >"+jarray[i].deptName+"</option>";
					continue;
				}
				html+="<option value="+jarray[i].did+">"+jarray[i].deptName+"</option>";
			}
			//写入
			deptName.innerHTML=html;
		}
	};
	//设置参数
	xhr.open("GET","${pageContext.request.contextPath}/empServlet.do?action=getDepts",true);
	//发送请求
	xhr.send();
}
</script>
</head>
<body>
<form  action="empServlet.do?action=query" method="post" role="form" class="form-inline">
	<div class="form-group">
		<label for="staffs">职位：</label>
		<select class="form-control" name="staffName" id="staffs">
			<option>--请选择--</option>
		</select>
	</div>
	<div class="form-group">
		<label for="empName">姓名：</label>
		<input type="text" name="empName" value="${pg.parms.empName==null?'':pg.parms.empName }" class="form-control" id="empName" placeholder="请输入姓名">
	</div>
	<div class="form-group">
		<label for="cardNum">身份证号码：</label>
		<input type="text" name="cardNum" value="${pg.parms.cardNum==null?'':pg.parms.cardNum }" class="form-control" id="cardNum" placeholder="请输入身份证号码">
	</div>
	<br/>
	<div class="form-group">
		<label for="staffs">性别：</label>
		<select class="form-control" name="gender">
			<option value="-1" ${pg.parms.gender==-1?"selected":"" }>--请选择--</option>
			<option value="0" ${pg.parms.gender==0?"selected":"" }>男</option>
			<option value="1" ${pg.parms.gender==1?"selected":"" }>女</option>
		</select>
	</div>
	<div class="form-group">
		<label for="telNum">手机：</label>
		<input type="text" name="telNum" value="${pg.parms.telNum==null?'':pg.parms.telNum }"  class="form-control" id="telNum" placeholder="请输入手机">
	</div>
	<div class="form-group">
		<label for="deptName">所属部门：</label>
		<select class="form-control" name="deptName" id="deptName">
		</select>
	</div>
	<br/>
	<input type="submit" value="搜索" class="btn btn-default">
</form>
<!--<form  action="empServlet.do?action=query" method="post">
		<table class="table table-bordered">
			<tr>
				<td>职位：</td>
				<td>
					<select name="staffName" id="staffs">
						<option>--请选择--</option>
					</select>
				</td>
				<td>姓名：</td>
				<td><input type="text" name="empName" value="${pg.parms.empName==null?'':pg.parms.empName }"></td>
				<td>身份证号码：</td>
				<td><input type="text" name="cardNum" value="${pg.parms.cardNum==null?'':pg.parms.cardNum }"></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
						<select name="gender">
						<option value="-1" ${pg.parms.gender==-1?"selected":"" }>--请选择--</option>
						<option value="0" ${pg.parms.gender==0?"selected":"" }>男</option>
						<option value="1" ${pg.parms.gender==1?"selected":"" }>女</option>
					</select>
				</td>
				<td>手机：</td>
				<td><input type="text" name="telNum" value="${pg.parms.telNum==null?'':pg.parms.telNum }" }></td>
				<td>所属部门：</td>
				<td>
					<select name="deptName" id="deptName">
					</select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="搜索"></td>
				<td></td>
			</tr>
		</table>
	</form>
-->
	<table class="table table-hover table-striped">
		<tr class="warning">
			<td><input type="checkbox"><td>
			<td>姓名<td>
			<td>性别<td>
			<td>手机号码<td>
			<td>邮箱<td>
			<td>职位<td>
			<td>学历<td>
			<td>身份证号码<td>
			<td>部门<td>
			<td>联系地址<td>
			<td>建档日期<td>
			<td>操作<td>
		</tr>
		
		<c:forEach var="emps" items="${pg.listData}">
		<tr>
			<td><input type="checkbox"><td>
			<td>${emps.empName}<td>
			<td>${emps.gender}<td>
			<td>${emps.telNum}<td>
			<td>${emps.email}<td>
			<td>${emps.staff.staffName}<td>
			<td>${emps.empEdu}<td>
			<td>${emps.cardNum}<td>
			<td>${emps.dept.deptName}<td>
			<td>${emps.empAddress}<td>
			<td>${emps.empCreateTime}<td>
			<td><input type="button" value="更新"><td>
		</tr>
		</c:forEach>
	</table>
<ul class="pagination">
	<c:if test="${pg.totalCount>0 }">
		<li><a href="empServlet.do?action=query&pageIndex=1&empName=${pg.parms.empName}&staffName=${pg.parms.staffId}&gender=${pg.parms.gender}&cardNum=${pg.parms.cardNum}&telNum=${pg.parms.telNum}&deptName=${pg.parms.dId}">首页</a></li>
		<c:forEach var="p" items="${pageList }">
			<c:if test="${p==pg.pageIndex }">
			<li class="active"><a href="empServlet.do?action=query&pageIndex=${p }&empName=${pg.parms.empName}&staffName=${pg.parms.staffId}&gender=${pg.parms.gender}&cardNum=${pg.parms.cardNum}&telNum=${pg.parms.telNum}&deptName=${pg.parms.dId}">${p }</a></li>
			</c:if>
			<c:if test="${p!=pg.pageIndex }">
			<li><a href="empServlet.do?action=query&pageIndex=${p }&empName=${pg.parms.empName}&staffName=${pg.parms.staffId}&gender=${pg.parms.gender}&cardNum=${pg.parms.cardNum}&telNum=${pg.parms.telNum}&deptName=${pg.parms.dId}">${p }</a></li>
			</c:if>
		</c:forEach>
		<li><a href="empServlet.do?action=query&pageIndex=${pg.totalPage }&empName=${pg.parms.empName}&staffName=${pg.parms.staffId}&gender=${pg.parms.gender}&cardNum=${pg.parms.cardNum}&telNum=${pg.parms.telNum}&deptName=${pg.parms.dId}">尾页</a></li>
	</c:if>
	<li><span>[第${pg.pageIndex }/${pg.totalPage }页]</span><li>
	<li><span>[共计${pg.totalCount}条]</span><li>
</ul>
</body>
</html>