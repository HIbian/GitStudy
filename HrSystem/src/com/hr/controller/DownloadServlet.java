package com.hr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hr.bean.Download;
import com.hr.bean.User;
import com.hr.servce.DownloadServce;
import com.hr.servce.DownloadServceimpl;

@MultipartConfig
@WebServlet("/DownloadServlet.do")
public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	DownloadServce ds = new DownloadServceimpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("upLoad".equals(action)) {//上传文档
			upLoad(request, response);
		}else if ("query".equals(action)) {
			//获取文件集合
			int totalCount = ds.getTotalCount();
			//获取文件列表
			List<Download> dls = ds.getDownloadFiles();
			request.setAttribute("dls", dls);
			request.setAttribute("totalCount", totalCount);
			request.getRequestDispatcher("downLoad.jsp").forward(request, response);
		}else if ("download".equals(action)) {//下载文件
			int downloadId = Integer.parseInt(request.getParameter("id"));
			//获取文件路径
			Download download = ds.getFileById(downloadId);
			String path = download.getDownPath();
			File file = new File(path);
			String fileName = file.getName();
			//下载文件,设置响应头，响应一个文件
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
			//用流的方式返回一个文件
			FileInputStream fis = new FileInputStream(path);//输入流
			ServletOutputStream sos = response.getOutputStream();//输出响应流
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=fis.read(buffer))!=-1) {
				sos.write(buffer, 0, len);
			}
			sos.close();
			fis.close();
		}
	}

	private void upLoad(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String downloadTitle = request.getParameter("downloadTitle");
		String downloadDes = request .getParameter("downloadDes");
		
		User user = (User)request.getSession().getAttribute("loginUser");
		int userId = user.getUserId();
		
		String downloadPath="";
		//文件
		Collection<Part> parts = request.getParts();
		for (Part p : parts) {//便利查找文件
			String header = p.getHeader("content-disposition");
			if (header.contains("file")) {//如果是文件parts
				//获取文件名
				String fileName = p.getSubmittedFileName();//1.jpg
				//根据时间跟新文件名
				fileName = newFileName(fileName);
//				File file = new File("F:\\test\\"+fileName);//存放路径
				File file = new File(fileName);//存放路径
				downloadPath = file.getAbsolutePath();
				p.write(downloadPath);//存储文件
			}
		}
		Download dl = new Download(downloadTitle,new SimpleDateFormat("yyyy-MM-dd").format(new Date()),userId, downloadDes, downloadPath);
		//保存到数据库中
		if (ds.uploadFile(dl)==1) {//上传成功
			response.sendRedirect("DownloadServlet.do?action=query");
		}else {//上传失败
			
		}
	}
	
	private String newFileName(String fileName) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+fileName;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
