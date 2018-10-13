<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.hr.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
User u1 = new User("chenxin",1);
User u2 = new User("chennuan",0);
List<User> users = new ArrayList<User>();
users.add(u1);
users.add(u2);
pageContext.setAttribute("user",users);

Map<String,User> maps = new HashMap<String,User>();
maps.put("u1", u1);
maps.put("u2", u2);
pageContext.setAttribute("usermap", maps);

%>
${user[0].userName }
${user[0].state }
${user[1].userName }
${user[1].state }
<br/>
${usermap.u1.userName }
${usermap.u1.state }
${usermap["u2"].userName }
${usermap["u2"].state }
<br/>
<% pageContext.setAttribute("time", new Date()); %>
<fmt:formatDate value="${time }"/><br/>
<fmt:formatDate value="${time }" type="date"/><br/>
<fmt:formatDate value="${time }" type="time"/><br/>
<fmt:formatDate value="${time }" type="both"/><br/>
<fmt:formatDate value="${time }" pattern="yyyy-MM-dd HH:mm:ss"/><br/>

</body>
</html>