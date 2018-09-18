package com.test02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Zhuce.do")
public class Zhuce extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		userDao uDao = new userDao();
		String username = req.getParameter("username");
		String userpass = req.getParameter("userpass");
		if (uDao.registered(username, userpass)) {
			req.setAttribute("xiaoxi", "注册成功");
			req.getRequestDispatcher("1.jsp").forward(req, resp);
		}else {
			req.setAttribute("xiaoxi", "注册失败");
			req.getRequestDispatcher("1.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
