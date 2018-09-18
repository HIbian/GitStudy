package com.test02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete.do")
public class deleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		userDao uDao = new userDao();
		if (uDao.delete(id)) {
			req.setAttribute("xiaoxi","删除成功");
			req.getRequestDispatcher("showall.do").forward(req, resp);
		}else {
			req.setAttribute("xiaoxi","删除失败");
			req.getRequestDispatcher("showall.do").forward(req, resp);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
