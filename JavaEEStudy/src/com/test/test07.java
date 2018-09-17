package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class test07 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servlet_info = request.getHeader("Accept");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		writer.println("<html>");
		writer.println("<head><title>com.test.test07</title></head>");
		
		writer.println("<body>");
		writer.println(servlet_info);
		writer.println("</body>");
		writer.println("</html>");
		writer.flush();
		writer.close();
	}

}
