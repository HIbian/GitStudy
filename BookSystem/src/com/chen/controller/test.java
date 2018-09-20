package com.chen.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test.do")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getMethod();
		StringBuffer url = request.getRequestURL();//网页地址
		String requestURI = request.getRequestURI();//相对web项目的地址
		String queryString = request.getQueryString();//浏览器？后面的那一串url?do=33322&key=123
		String remoteAddr = request.getRemoteAddr();
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		String remoteUser = request.getRemoteUser();
		System.out.println("method-"+ method
				+ "\nurl-" +url
				+ "\nrequestURI-" +requestURI
				+ "\nqueryString-" +queryString
				+ "\nremoteAddr-" +remoteAddr
				+ "\nremoteHost-" +remoteHost
				+ "\nremotePort-" +remotePort
				+ "\nremoteUser-" +remoteUser); 
		//获取所有header的name
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String nextElement = headerNames.nextElement();
			System.out.println(nextElement+":"+request.getHeader(nextElement));
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
