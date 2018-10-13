package com.hr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class filterDemo2 implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//转换对象
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//禁止浏览器缓存所有动态页面
		resp.setDateHeader("Expires", -1);
		resp.setHeader("Cache-Control","no-cache");
		resp.setHeader("Pragma", "no-cache");
		//放行
		chain.doFilter(req, resp);
	}
}
