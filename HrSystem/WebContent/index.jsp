<%@page import="java.util.ArrayList"%>
<%@page import="com.hr.bean.User"%>
<%@page import="com.hr.utils.pageHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <% 
int[] pageIndexList = (int[])request.getAttribute("pageIndexList");
pageHelper ph = (pageHelper)request.getAttribute("ph"); 
User parms = ph.getParms();
int pageIndex = ph.getPageIndex();
int pageSize = ph.getPageSize();
int totalCount = ph.getTotalCount();
ArrayList<User> userPage = (ArrayList<User>)ph.getUserPage();
int totalPage =totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
%>
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
<style type="text/css">

</style>
<script type="text/javascript">
$(function(){
	  /** 给全选按钮绑定点击事件  */	
   	$("#checkAll").click(function(){
   		// this是checkAll  this.checked是true
   		// 所有数据行的选中状态与全选的状态一致
  		$("input[name='ck']").prop("checked",this.checked);
   	});
   	/** 删除选中的 */
   	$("#delete").click(function(){
   		var chk_v=[];
   		$("#userData").find("input[name='ck']:checked").each(function(){
   			chk_v.push($(this).val());
   		});
   		alert(chk_v);
   		location.href = "UserServlet.do?action=delete&userIds="+chk_v;
   	});
})
</script>
</head>
<body>

<!-- 数据查询 -->
<form action="UserServlet.do?action=index" method="post" role="form" class="form-inline">
	<div class="form-group">
		<label for="name">用户名：</label>
		<input type="text" class="form-control" id="name" name="userName" placeholder="请输入用户名" value="<%=parms.getUserName()%>">
	</div>
	<div class="form-group">
		<label for="name">用户状态：</label>
		<select class="form-control" name="state">
			<option value="-1" <%=parms.getState()==-1?"selected":"" %>>全部</option>
			<option value="1" <%=parms.getState()==1?"selected":"" %>>启用</option>
			<option value="0" <%=parms.getState()==0?"selected":"" %>>禁用</option>
		</select>
	</div>
	<input type="submit" class="btn btn-default" value="查询">
	<input type="button" class="btn btn-danger" value="删除" id="delete">
</form>
<!--
	<table  cellpadding="0" cellspacing="0">
		<form action="UserServlet.do?action=index" method="post">
			<tr>
				<td>用户名:<input type="text" name="userName" value="<%=parms.getUserName()%>"></td>
				<td>用户状态:
					<select name="state">
						<option value="-1" <%=parms.getState()==-1?"selected":"" %>>全部</option>
						<option value="1" <%=parms.getState()==1?"selected":"" %>>启用</option>
						<option value="0" <%=parms.getState()==0?"selected":"" %>>禁用</option>
					</select>
				</td>
				<td><input type="submit" value="查询"></td>
				<td><input type="button" value="删除" id="delete"></td>
			</tr>
		</form>
	</table>
-->
<!-- 数据展示 -->

<table class="table table-hover table-bordered table-striped" id="userData">
	<tr class="warning">
		<td><input type="checkbox" name="checkAll" id="checkAll"></td>
		<td>登录名</td>
		<td>密码</td>
		<td>用户名</td>
		<td>状态</td>
		<td>创建时间</td>
		<td align="center">操作</td>
	</tr>
	<% for(User us:userPage){%>
		<tr id="data_">
			<td><input type="checkbox" id="u_<%=us.getUserId() %>" name="ck" value="<%=us.getUserId()%>"></td>
			<td><%=us.getUserLoginName() %></td>
			<td><%=us.getUserPwd() %></td>
			<td><%=us.getUserName() %></td>
			<td><%=us.getState()==1?"开启":"禁用" %></td>
			<td><%=us.getCreateTime() %></td>
			<td><a href="UserServlet.do?action=updateUser&userId=<%=us.getUserId()%>">修改</a></td>
		</tr>
	<%	} %>
</table>
<ul class="pagination">
<li><a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=1">首页</a>&nbsp;</li>
<li><a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=<%=pageIndex-1==0?1:(pageIndex-1) %>">上一页</a>&nbsp;</li>
<%if(pageIndexList.length>0){ %>
	
	<c:forEach begin="<%=pageIndexList[0] %>" end="<%=pageIndexList[pageIndexList.length-1] %>" step="1" var="i">
		<c:if test="${i==ph.pageIndex }">
			<li class="active"><a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=${i}">${i}</a></li>
		</c:if>
		<c:if test="${i!=ph.pageIndex }">
			<li><a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=${i}">${i}</a></li>
		</c:if>
	</c:forEach>
	
<% }%>
<li><a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=<%=pageIndex+1==(totalPage+1)?totalPage:(pageIndex+1) %>">下一页</a>&nbsp;</li>
<li><a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=<%=totalPage %>">尾页</a></li>

<li><span>[<%=pageIndex %>/<%=totalPage %>]</span></li>
<li><span>[共<%=totalCount %>条]</span></li>
</ul>
</body>
</html>