package com.chen.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.chen.bean.User;
import com.chen.servce.userservce;
import com.chen.servce.userservceimpl;

@WebServlet("/UserServlet.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//传给逻辑层
	userservce us = new userservceimpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码统一
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//获取需要执行的action
		String action = request.getParameter("action");
		if ("login".equals(action)) {
			user_login(request, response);
		}else if ("register".equals(action)) {
			user_register(request, response);
		}
	}

	private void user_register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User u = new User();
		try {
			//将表单中的数据封装到User对象属性中去，属性名需要的相应的表单name相同
			BeanUtils.populate(u, request.getParameterMap());
			System.out.println(u);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		int count = us.register(u);
		if (count==1) {
			//注册成功->登陆页面
			response.getWriter().write("<script>alert('注册成功!');location.href='login.jsp'</script>");
//			response.sendRedirect("login.jsp");
		}else {
			//注册失败->注册页面
			response.getWriter().write("<script>alert('注册失败!');location.href='register.jsp'</script>");
//			response.sendRedirect("register.jsp");
		}
	}

	private void user_login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//登陆
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//验证信息
		int count = us.login(new User(username, password));
		if (count==1) {
			//登陆成功，跳转至显示图书界面
			response.sendRedirect("BookServlet.do?action=showall");
		}else {
			//登陆失败，跳转至登陆界面
			response.getWriter().write("<script>alert('账号或者密码错误!');location.href='login.jsp'</script>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
