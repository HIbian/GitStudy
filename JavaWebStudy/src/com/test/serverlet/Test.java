package com.test.serverlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//实现servlet接口
public class Test implements Servlet {
	
	//只被调用一次，第一次请求servlet时，调用构造器
	public Test() {
		System.out.println("调用构造器Test()...只被调用一次，第一次请求servlet时，调用构造器");
	}
	
	//web应用被卸载前调用，用于释放资源
	@Override
	public void destroy() {
		System.out.println("destroy()");
	}

	//用于初始化servlet，把servlet装入内存中，只被调用一次，创建好实例后立即调用
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("调用初始化方法init()...用于初始化servlet，把servlet装入内存中，只被调用一次，创建好实例后立即调用");
	}
	
	//会被调用多次，每次请求都会调用service方法。用于响应请求
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("执行主方法体service()...");
		Enumeration<String> parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String  pname = parameterNames.nextElement();
			String[] value = req.getParameterValues(pname);
			System.out.println(value[0]+"\n");
		}
		
	}
	
	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
