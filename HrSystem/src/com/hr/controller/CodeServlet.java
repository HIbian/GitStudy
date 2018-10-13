package com.hr.controller;

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
import javax.servlet.http.HttpSession;

@WebServlet("/CodeServlet.do")
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("img/jpeg");
		//宽高
		int height=40;
		int width=100;
		//画布
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//画笔
		Graphics g= img.getGraphics();
		g.setColor(getRandomColor());
		g.fillRect(0, 0, width, height);
		g.setColor(getRandomColor());
		g.drawRect(0, 0, width-1, height-1);
		
		//字符
		String chars_str ="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] chars = chars_str.toCharArray();
		//创建验证码对象
		StringBuffer  sb = new StringBuffer();
		Random r = new Random();
		//设置字体
		g.setFont(new Font("Arial",Font.BOLD,17));
		//生成4个随机的字符
		for (int i = 0; i < 4; i++) {
			char c = chars[r.nextInt(chars.length)];
			sb.append(c);
			//画图
			g.setColor(getRandomColor());
			g.drawString(String.valueOf(c), width/6*(i+1), height/2+height/12*r.nextInt(3));
		}
		//添加干扰线
		for(int i = 0;i<4;i++) {
			g.setColor(getRandomColor());
			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}
		//将验证码存入session中
		HttpSession session = request.getSession();
		session.setAttribute("code", sb);
		System.out.println(sb);
		//输出验证图片
		ImageIO.write(img, "jpg", response.getOutputStream());
		return;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	private Color getRandomColor() {
		Random r=  new Random();
		int red = r.nextInt(256);
		int green= r.nextInt(256);
		int blue = r.nextInt(256);
		return new Color(red, green, blue);
	}
}
