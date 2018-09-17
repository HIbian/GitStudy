package com.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 这个小程序是用来演示以下两个小知识点
 *  1、使用GZIPOutputStream流来压缩数据
 *  2、设置响应头Content-Encoding来告诉浏览器，服务器发送回来的数据压缩后的格式
 */
public class test02 extends HttpServlet{
	private static final long serialVersionUID = -8571797314334496154L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String data = "abcdefghijklmnopqrstuvwxyz"
				+ "abcdefghijklmnopqrstuvwxyz"
				+ "abcdefghijklmnopqrstuvwxyz"
				+ "abcdefghijklmnopqrstuvwxyz"
				+ "abcdefghijklmnopqrstuvwxyz"
				+ "abcdefghijklmnopqrstuvwxyz"
				+ "abcdefghijklmnopqrstuvwxyz"
				+ "abcdefghijklmnopqrstuvwxyz"
				+ "abcdefghijklmnopqrstuvwxyz";
		System.out.println("原始数据的大小为：" + data.getBytes().length);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(baos);//buffer
		gos.write(data.getBytes());
		gos.close();
		//得到压缩后的数据
		byte g[] = baos.toByteArray();
		resp.setHeader("Content-Encoding", "gzip");
		resp.setHeader("Content-Length", g.length+"");
		resp.getOutputStream().write(g);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
