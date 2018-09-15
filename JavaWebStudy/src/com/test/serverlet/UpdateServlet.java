package com.test.serverlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDaoImpl;
import com.entity.User;
public class UpdateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id")); 
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String sex = req.getParameter("sex");
		String home = req.getParameter("home");
		String info = req.getParameter("info");
		UserDaoImpl uDao = new UserDaoImpl();
		if (uDao.update(new User(id,name, pwd, sex, home, info))) {
			req.setAttribute("xiaoxi", "更改成功");
			req.getRequestDispatcher("/showAllServlet").forward(req, resp);
		}else {
			req.setAttribute("xiaoxi", "更改失败");
			req.getRequestDispatcher("/showAllServlet").forward(req, resp);
		}
	}
}
