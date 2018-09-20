package com.chen.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CodeServlet.do")
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//宽高
		int width=100;
		int height=40;
		//画布
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//画笔
		Graphics g = img.getGraphics();
		//画背景
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		//画边框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width-1, height-1);
		
		//字符
		String str_chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		char[] charArray = str_chars.toCharArray();
		
		//获取生成的字符
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		g.setFont(new Font("Arial",Font.BOLD,15));
		for(int i =0;i<4;i++) {
			char c = charArray[r.nextInt(charArray.length)];
			
			sb.append(c);
			int red = r.nextInt(256);
			int green = r.nextInt(256);
			int blue = r.nextInt(256);
			g.setColor(new Color(red, green, blue));
			g.drawString(String.valueOf(c), (i)*width/5+width/10, r.nextInt(20)+20);
		}
		System.out.println(sb);
		//画干扰线
		for(int i =0;i<8;i++) {
			//设置颜色
			int red = r.nextInt(256);
			int green = r.nextInt(256);
			int blue = r.nextInt(256);
			g.setColor(new Color(red, green, blue));
			//设置坐标
			int x1 = r.nextInt(width);
			int y1 = r.nextInt(height);
			int x2 = r.nextInt(width);
			int y2 = r.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		
		//输出验证图片
		ImageIO.write(img, "jpg", response.getOutputStream());
		response.setContentType("image/jpeg");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
