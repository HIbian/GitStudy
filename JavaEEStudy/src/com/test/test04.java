package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//设置refresh响应头，让浏览器定时刷新
public class test04 extends HttpServlet{
	private static final long serialVersionUID = -2881962209124737416L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 设置refresh响应头，让浏览器每隔3秒定时刷新
		 */
		// resp.setHeader("refresh", "3");
		/**
		 * 设置refresh响应头，让浏览器3秒后跳转到http://www.baidu.com
		 */
		resp.setHeader("refresh", "3;http://www.baidu.com");
		resp.getWriter().write("3s later to Baidu");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
