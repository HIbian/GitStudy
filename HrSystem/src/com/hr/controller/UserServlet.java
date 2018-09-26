package com.hr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.bean.User;
import com.hr.servce.UserServce;
import com.hr.servce.UserServceImpl;
import com.hr.utils.pageHelper;
@WebServlet("/UserServlet.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserServce us = new UserServceImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//获取需要执行的方法
		String action = request.getParameter("action");
		if ("index".equals(action)) {
			queryPage(request, response);
		}else if ("addUser".equals(action)) {
			
		}else if ("updateUser".equals(action)) {
			
		}
	}
	private void queryPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName")==null?"":request.getParameter("userName");//第一次进入index时，为null
		int state = request.getParameter("state")==null?-1:Integer.parseInt(request.getParameter("state"));
		//获取页码，第一次进入为null，需要设置初始值
		int pageIndex = request.getParameter("indexPage")==null?1:Integer.parseInt( request.getParameter("indexPage"));
		//设置查询条件
		User userT = new User(userName, state);
		//设置分页每一页大小
		int pageSize = 5;
		//获取查询结果
		int totalCount = us.getTotalCount(userT);
		List<User> userPage = us.getUserPage(userT, pageIndex, pageSize);
		//封装为对象传递给jsp,需要做数据回显，需要传入的值有userT，pageIndex，pageSize，totalCount，userPage
		pageHelper ph = new pageHelper(userT, pageIndex, pageSize, totalCount, userPage);
		//设置attribute并传入jsp中
		request.setAttribute("ph", ph);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
