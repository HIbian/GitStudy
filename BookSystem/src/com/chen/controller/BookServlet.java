package com.chen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chen.bean.Books;
import com.chen.servce.bookservce;
import com.chen.servce.bookservceimpl;

@WebServlet("/BookServlet.do")
public class BookServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	bookservce bs = new bookservceimpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//统一编码格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		//获取行动参数
		String action = req.getParameter("action");
		if ("showall".equals(action)) {
			book_showall(req, resp);
		}else if ("delete".equals(action)) {
			book_delete(req, resp);
		}else if ("addbook".equals(action)) {
			book_add(req, resp);
		}else if ("update".equals(action)) {
			int bid = Integer.parseInt(req.getParameter("bid"));
			req.setAttribute("book", bs.getbookById(bid));
			req.getRequestDispatcher("update.jsp").forward(req, resp);
		}else if ("doupdate".equals(action)) {
			//更新
			int count = bs.updatebook(new Books(Integer.parseInt(req.getParameter("bid")),req.getParameter("bname"), req.getParameter("bauthor"), Double.parseDouble(req.getParameter("bprice")), req.getParameter("bdate"), req.getParameter("bimage"), Integer.parseInt(req.getParameter("bisonline"))));
			if (count==1) {
				//更新成功
				resp.getWriter().write("<script>alert('更新成功！');location.href='BookServlet.do?action=showall';</script>");
			}else {
				//更新失败
				resp.getWriter().write("<script>alert('更新失败！');location.href='BookServlet.do?action=showall';</script>");
			}
		}
	}
	private void book_add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int count = bs.addbook(new Books(req.getParameter("bname"), req.getParameter("bauthor"), Double.parseDouble(req.getParameter("bprice")), req.getParameter("bdate"), req.getParameter("bimage"), Integer.parseInt(req.getParameter("bisonline"))));
		if (count==1) {
			//添加成功->showall
			resp.sendRedirect("BookServlet.do?action=showall");
		}else {
			//添加失败->添加界面
			resp.sendRedirect("AddBook.jsp");
		}
	}
	private void book_delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int bid = Integer.parseInt(req.getParameter("bid"));
		int count =bs.deletebook(bid);
		if (count ==1) {
			//删除成功，再次查询跳转
			resp.getWriter().write("<script>alert('删除成功！');location.href='BookServlet.do?action=showall';</script>");
		}else {
			//TODO 删除失败
		}
	}
	private void book_showall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//将数据转发到books.jsp
		req.setAttribute("booklist", bs.getbooks());
		req.getRequestDispatcher("Books.jsp").forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
