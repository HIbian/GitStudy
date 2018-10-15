package com.dangdang.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 设置编码
 */
@WebFilter("/*")
public class EncodeFilter extends HttpFilter{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		chain.doFilter(req, res);
	}
}
