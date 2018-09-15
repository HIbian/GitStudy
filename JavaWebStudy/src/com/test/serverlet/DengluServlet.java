package com.test.serverlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDaoImpl;

public class DengluServlet extends HttpServlet{
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
		String name = req.getParameter("username");
		String pwd = req.getParameter("password");
		UserDaoImpl userdao = new UserDaoImpl();
		if(userdao.login(name, pwd)) {
			req.setAttribute("xiaoxi", "欢迎"+name); //向reques域中存放信息
			req.getRequestDispatcher("/loginS.jsp").forward(req, resp);//转发到成功页面,通过forward()方法将提交信息在多个页面间进行传递
		}else {
			resp.sendRedirect("index.jsp");
		}
	}
}
