package com.test.serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDaoImpl;
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_s = request.getParameter("id");
		int id = Integer.valueOf(id_s);
		UserDaoImpl uDao = new UserDaoImpl();
		if (uDao.delete(id)) {
			request.setAttribute("xiaoxi", "删除成功");
			request.getRequestDispatcher("/showAllServlet").forward(request, response);
		}else {
			request.setAttribute("xiaoxi", "删除失败");
			request.getRequestDispatcher("/showAllServlet").forward(request, response);
		}
	}

}
