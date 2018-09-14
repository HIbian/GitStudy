package com.test.serverlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class testservlet2 extends HttpServlet{
	
	//构造方法
	public testservlet2() {
		System.out.println("构造器()");
	}
	
	//加载类到内存
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init()");
	}
	
	//处理post请求
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("dopost");
	}
	
	//处理get请求
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      // 设置响应内容类型
	      resp.setContentType("text/html");

	      // 实际的逻辑是在这里
	      PrintWriter out = resp.getWriter();
	      out.println("<h1>Hello World</h1>");
	}
	
	@Override
	public void destroy() {
	}
}
