<%@page import="java.util.ArrayList"%>
<%@page import="com.hr.bean.User"%>
<%@page import="com.hr.utils.pageHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% 
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
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
$(function(){
	    /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
	$("tr[id^='data_']").hover(function(){
		$(this).css("backgroundColor","#eeccff");
	},function(){
		$(this).css("backgroundColor","#ffffff");
	})
})
</script>
</head>
<body>

<!-- 数据查询 -->
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
<!-- 数据展示 -->

<table cellpadding="0" cellspacing="0">
	<tr>
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
			<td><input type="checkbox"></td>
			<td><%=us.getUserLoginName() %></td>
			<td><%=us.getUserPwd() %></td>
			<td><%=us.getUserName() %></td>
			<td><%=us.getState()==1?"开启":"禁用" %></td>
			<td><%=us.getCreateTime() %></td>
			<td><a href="#">修改</a></td>
		</tr>
	<%	} %>
</table>
<a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=1">首页</a>&nbsp;
<a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=<%=pageIndex-1==0?1:(pageIndex-1) %>">上一页</a>&nbsp;
<a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=<%=pageIndex+1==(totalPage+1)?totalPage:(pageIndex+1) %>">下一页</a>&nbsp;
<a href="UserServlet.do?action=index&userName=<%=parms.getUserName()%>&state=<%=parms.getState()%>&indexPage=<%=totalPage %>">尾页</a>
&nbsp;<span><%=pageIndex %>/<%=totalPage %></span>
</body>
</html>