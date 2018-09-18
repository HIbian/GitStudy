package com.test02;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(value = "/hello", loadOnStartup = 1)
@WebServlet("/test01.do")
public class test01 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		userDao uDao = new userDao();
		String username = req.getParameter("username");
		String userpass = req.getParameter("userpass");
		if (uDao.isExist(username, userpass)) {
			req.setAttribute("xiaoxi", "登陆成功");
			req.getRequestDispatcher("1.jsp").forward(req, resp);
		}else {
			req.setAttribute("xiaoxi", "登陆失败");
			req.getRequestDispatcher("1.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
