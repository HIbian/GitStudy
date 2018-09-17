package com.test;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//指定返回给浏览器的数据类型
public class test03 extends HttpServlet{
	private static final long serialVersionUID = 3295417844767266699L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 /**
         * 浏览器能接收(Accept)的数据类型有: 
         * application/x-ms-application, 
         * image/jpeg, 
         * application/xaml+xml, 
         * image/gif, 
         * image/pjpeg, 
         * application/x-ms-xbap, 
         * application/vnd.ms-excel, 
         * application/vnd.ms-powerpoint, 
         * application/msword, 
         */
		//设置相应头的数据类型
		resp.setHeader("conent-type", "img/jpeg");
		InputStream in = this.getServletContext().getResourceAsStream("/img/3.png");
		byte[] buf = new byte[1024];
		int len = 0;
		while((len=in.read(buf))>0) {
			resp.getOutputStream().write(buf,0,len);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
