package com.test;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class test05 extends HttpServlet{
	private static final long serialVersionUID = -5737017420793599843L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("content-disposition", "attachment;filename=3.png");
		InputStream in = this.getServletContext().getResourceAsStream("img/3.png");
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len=in.read(buf))>0) {
			resp.getOutputStream().write(buf, 0, len);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
