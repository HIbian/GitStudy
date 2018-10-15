package com.dangdang.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.dangdang.bean.User;
import com.dangdang.servce.UserServce;
import com.dangdang.servce.UserServceimpl;

@WebServlet("/UserServlet.do")
public class UserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	UserServce us = new UserServceimpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action =  req.getParameter("action");
		System.out.println(action);
		if ("register".equals(action)) {//用户注册
			register(req);
		}else if ("isusername".equals(action)) {//验证用户名是否合法
			isusername(req, resp);
		}else if ("ispassword".equals(action)) {//验证密码是否合法
			ispassword(req, resp);
		}else if ("isemail".equals(action)) {//验证邮箱是否合法
			isemail(req, resp);
		}else if ("login".equals(action)) {//用户登陆
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String code = req.getParameter("code");
			//获取服务器生成的验证码
			String server_code = req.getSession().getAttribute("code").toString();
			if (!server_code.equals(code)) {//验证码不正确
				req.setAttribute("message", "验证码不正确");
				req.getRequestDispatcher("userLogin.jsp").forward(req, resp);
				return;
			}
			User u = us.userLogin(username,password);
			if (u==null) {//账号或者密码不正确
				req.setAttribute("message", "账号或密码不正确");
				req.getRequestDispatcher("userLogin.jsp").forward(req, resp);
				return;
			}
			//登陆成功，存sesion
			req.getSession().setAttribute("user", u);
			//跳转到主页面
			System.out.println("登陆成功");
		}
	}
	private void isemail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String email = req.getParameter("email");
		if (us.isEmail(email)) {
			resp.getWriter().write("true");
		}else {
			resp.getWriter().write("false");
		}
	}
	private void ispassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String password = req.getParameter("password");
		if (us.isPassword(password)) {
			resp.getWriter().write("true");
		}else {
			resp.getWriter().write("false");
		}
	}
	private void isusername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		if (us.isUserName(username)) {
			resp.getWriter().write("true");
		}else {
			resp.getWriter().write("false");
		}
	}
	private void register(HttpServletRequest req) {
		User u = new User();
		try {
			BeanUtils.populate(u, req.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		int reNum = us.userRegister(u);
		if (reNum==1) {//注册成功
			System.out.println("注册成功");
		}else if (reNum==-1) {//数据库错误
			System.out.println("数据库错误");
		}else if (reNum==-2) {//用户名重复
			System.out.println("用户名重复");
		}else if (reNum==-3) {//邮箱重复
			System.out.println("邮箱重复");
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
