package com.hr.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class filterDemo implements Filter {
	
	private String encode="";
	
	public void init(FilterConfig fConfig) throws ServletException {
		encode = fConfig.getInitParameter("encode");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//设置编码格式
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding(encode);
		resp.setContentType("text/html;charset=utf-8");
		chain.doFilter(req, resp);
	}
}
