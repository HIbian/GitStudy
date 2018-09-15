package com.test.serverlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDaoImpl;
import com.entity.User;

public class ZhuceServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String sex = req.getParameter("sex");
		String home = req.getParameter("home");
		String info = req.getParameter("info");
		User u = new User(0,name, pwd, sex, home, info);
		UserDaoImpl udao= new UserDaoImpl();
		if (udao.register(u)) {
			req.setAttribute("xiaoxi", "欢迎来自"+home+"的"+name);
			req.getRequestDispatcher("/loginS.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("Zhuce.jsp");
		}
	}
}
