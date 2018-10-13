package com.hr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.bean.User;

//登陆权限验证,拦截除了登陆界面jsp以及生成验证码servlet
@WebFilter(value="*.jsp")
public class filterDemo3 implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//转换为httpservlet对象
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//获取url地址，判断是否为不需要拦截的地址
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		//获取url想要执行的方法
		String action = req.getParameter("action");
		System.out.println(action);
		//获取session中的对象
		User loginUser = (User)req.getSession().getAttribute("loginUser");
		//loginUser有对象则表示用户已经登陆，不用拦截
		if (loginUser!=null) {
			chain.doFilter(req, resp);
			return;
		}
		//可以不需要登陆的url，不用拦截,例如登陆界面和提供验证码的页面
		if ("login".equals(action) || requestURI.contains("login.jsp") || requestURI.contains("CodeServlet")) {
			chain.doFilter(req, resp);
			return;
		}
		//没有放行的重定向
		resp.sendRedirect("login.jsp");
	}
}
