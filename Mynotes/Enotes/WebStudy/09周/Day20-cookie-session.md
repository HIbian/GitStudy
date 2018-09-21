# 会话管理
* http协议是**无状态的**：提出请求，接收响应后，信息就扔掉了
* 客户发来一个新的请求，服务器无法得知是否有联系
* request:私有局部的
* servletContext:公有全局的
* 会话跟踪：一个客户端发起的**一系列请求**，web服务器将其关联起来
* 客户端状态管理技术：保存在**浏览器，cookie**
* 服务器端状态管理技术：保存在**服务器端内存，session**
  * 服务器传递sessionID需要使用Coolie的方式

## Cookie
* Cookie中以**键值对**存储信息
* 每个浏览器独享一份Cookie
* 创建Cookie
* 获取Cookie
  * for循环
* 修改Cookie
  * 采用新建同名cookie，再覆盖的方式
* 设置Cookie生效时间
  * `setMaxAge(毫秒)`
  * 大于0:毫秒
  * 0：失效
  * 小于0：内存储存，关闭浏览器后失效
* 设置Cookie路径
>cookie 一般都是由于用户访问页面而被创建的，可是并不是只有在创建 cookie 的页面才可以访问这个cookie。在默认情况下，出于安全方面的考虑：
1、服务器端每次访问的cookie是每次请求头中发送给服务器端的
2、客户端每次请求只发送当前路径下和“直系”关系的父路径的cookie（父路径的页面是不能访问子路径和兄弟路径的cookie的）
3、setcookie如果不设置路径，默认为当前页面的路径，父亲路径的页面是无法访问的
4  "/"这个根路径可以在任何路径下访问，求简单可以把cookie都放在这里。
/ 路径 这个网站所有地方都可以取出来
/abc/xyz/cookie  路径 在 /abc/xyz/index.jsp   /abc/index.jsp  获取不到
* 案例：客户端上一次访问时间
  ```java
  package com.chen.controller;
  import java.io.IOException;
  import java.text.SimpleDateFormat;
  import java.util.Date;
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.Cookie;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  @WebServlet("/CookieTest")
  public class CookieTest extends HttpServlet {
  	private static final long serialVersionUID = 1L;

  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		response.setContentType("text/html;charset=utf-8");
  		boolean isFirst=true;
  		String time="";
  		//如果没有对应Cookie创建Cookie
  		Cookie[] cookies = request.getCookies();
  		if (cookies!=null) {
  			for (Cookie c : cookies) {
  				String name = c.getName();
  				if ("visit".equals(name)) {
  					time = c.getValue();
  					//创建键值对
  					Cookie ck = new Cookie("visit", new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
  					//设置路径
  					ck.setPath("/");
  					//设置生效时间(s)
  					ck.setMaxAge(60);
  					response.addCookie(ck);
  					isFirst = false;
  					break;
  				}
  			}
  		}
  		//第一次访问，创建Cookie
  		if (isFirst) {
  			//创建键值对
  			Cookie c = new Cookie("visit", new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
  			//设置路径
  			c.setPath("/");
  			//设置生效时间(s)
  			c.setMaxAge(60);
  			//让浏览器添加Cookie
  			response.addCookie(c);
  		}
  		if (isFirst) {
  			response.getWriter().write("第一次访问");
  		}else {
  			response.getWriter().write("欢迎回来,上次访问时间为"+time);
  		}

  	}
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		doPost(request, response);
  	}
  }
  ```
* Cookie编码与解码
  * 编码可以使用java.net.URLEncoder类的`encode(String str,String encoding)`方法，
  * 解码使用java.net.URLDecoder类的`decode(String str,String encoding)`方法
* 案例：登陆时显示填入账号密码信息
  ```html
    <%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
  <!DOCTYPE html>
  <html>
  <head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <script type="text/javascript">
  	function huan(){
  		var img = document.getElementById("code");
  		img.src="CodeServlet.do?"+Math.random();
  	}
  </script>
  </head>
  <body>
  <%
  	Cookie[] cs = request.getCookies();
  	String username="";
  	String password="";
  	if(cs!=null){
  		for(Cookie c:cs){
  			if("loginfo_username".equals(c.getName())){
  				username = c.getValue();
  			}
  			if("loginfo_password".equals(c.getName())){
  				password = c.getValue();
  			}
  		}
  	}
  %>
  <form action="UserServlet.do?action=login" method="post">
  用户名：<input type="text" name="username" value="<%=username%>"><br/>
  密码：<input type="password" name="password" value="<%=password%>"><br/>
  验证码：<input type="text" name="code" ><br/><img src="CodeServlet.do" id="code"><a href="#" onclick="huan()">看不清？换一张</a><br/>
  记住密码<input type="checkbox" name="rember" value="1"> <br/>
  <input type="submit" value="登陆"><br/>
  没有账号？<a href="register.jsp">点击注册</a>
  </form>
  </body>
  </html>
  ```
* Cookie的不可跨域名性


## HttpSession
*
