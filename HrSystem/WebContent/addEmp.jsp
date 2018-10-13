<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript">
window.onload=function(){
	getStaffs();
	getDepts();
}
function getStaffs(){
	//获取对象
	var staff = document.getElementById("staffs");
	//创建对象
	var xhr = new XMLHttpRequest();
	//设置回掉函数
	xhr.onreadystatechange=function(){
		//表示服务器相应成功
		if(xhr.readyState==4 && xhr.status==200){
			var data = xhr.responseText;//获取响应的对象，json；
			//alert(data);
			var html="<option value='-1' >--请选择--</option>";
			var js = JSON.parse(data.trim());
			for(var i=0;i<js.length;i++){
				html+="<option value="+js[i].staffId+" >"+js[i].staffName+"</option>"
			}
			staff.innerHTML=html;
		}
	};
	//设置请求信息
	xhr.open("GET","${pageContext.request.contextPath}/empServlet.do?action=getStaffs",true);
	//发送请求
	xhr.send();
}
function getDepts(){
	//获取对象
	var staff = document.getElementById("depts");
	//创建对象
	var xhr = new XMLHttpRequest();	
	//设置回掉函数
	xhr.onreadystatechange=function(){
		//表示服务器相应成功
		if(xhr.readyState==4 && xhr.status==200){
			var data = xhr.responseText;//获取响应的对象，json；
			//alert(data);
			var html="<option value='-1' >--请选择--</option>";
			var js = JSON.parse(data.trim());
			for(var i=0;i<js.length;i++){
				html+="<option value="+js[i].did+" >"+js[i].deptName+"</option>"
			}
			staff.innerHTML=html;
		}
	};
	//设置请求信息
	xhr.open("GET","${pageContext.request.contextPath}/empServlet.do?action=getDepts",true);
	//发送请求
	xhr.send();
}
</script>
<body>
<form action="empServlet.do?action=addEmp" method="post">
<table>
	<tr>
		<td>员工姓名：</td><td><input type="text" name="empName"></td>
	</tr>
	<tr>
		<td>性别：</td>
		<td>
			<input type="radio" name="gender" value="0" checked>男
			<input type="radio" name="gender" value="1">女
		</td>
	</tr>
	<tr>
		<td>电话号码：</td><td><input type="text" name="telNum"></td>
	</tr>
	<tr>
		<td>电子邮箱：</td><td><input type="text" name="email"></td>
	</tr>
	<tr>
		<td>职位：</td>
		<td>
			<select name="staffId" id="staffs">
				
			</select>
		</td>
	</tr>
	<tr>
		<td>学历：</td><td><input type="text" name="empEdu"></td>
	</tr>
	<tr>
		<td>身份证号码：</td><td><input type="text" name="cardNum"></td>
	</tr>
	<tr>
		<td>部门：</td>
		<td>
		<select name="dId" id="depts">
		
		</select>
		</td>
	</tr>
	<tr>
		<td>地址：</td><td><input type="text" name="empAddress"></td>
	</tr>
	<tr>
		<td>remark：</td><td><input type="text" name="remark"></td>
	</tr>
	<tr>
		<td>爱好：</td><td><input type="text" name="habiit"></td>
	</tr>
	<tr>
		<td>政治面貌：</td><td><input type="text" name="political"></td>
	</tr>
	<tr>
		<td>qq：</td><td><input type="text" name="qq"></td>
	</tr>
	<tr>
		<td>邮编：</td><td><input type="text" name="ems"></td>
	</tr>
	<tr>
		<td>生日：</td><td><input type="date" name="birth"></td>
	</tr>
	<tr>
		<td>专业：</td><td><input type="text" name="major"></td>
	</tr>
	<tr>
		<td>民族：</td><td><input type="text" name="volk"></td>
	</tr>
	<tr>
		<td>座机：</td><td><input type="text" name="phone"></td>
	</tr>
	<tr>
		<td><input type="submit" value="确认添加"></td><td></td>
	</tr>
</table>
</form>
</body>
</html>