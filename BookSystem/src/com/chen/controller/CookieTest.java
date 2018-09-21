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
