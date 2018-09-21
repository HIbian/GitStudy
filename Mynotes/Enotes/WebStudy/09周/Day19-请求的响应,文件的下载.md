# 请求和响应,文件的下载
## 请求参数封装
导包
![](img/10.png)
```html
<form action="UserServlet.do?action=register" method="post">
用户名：<input type="text" name="username" ><br/>
密码：<input type="password" name="password" ><br/>
性别：<input type="radio" name="sex" value="1" checked="checked">男
	  <input type="radio" name="sex" value="2">女<br/>
生日：<input type="text" name="birthday" ><br/>
email：<input type="text" name="email" ><br/>
<input type="submit" value="提交">&nbsp;
<input type="reset" value="重置">
```
```java
User u = new User();
try {
	//将表单中的数据封装到User对象属性中去，属性名需要的相应的表单name相同
	BeanUtils.populate(u, request.getParameterMap());
	System.out.println(u);
} catch (IllegalAccessException | InvocationTargetException e) {
	e.printStackTrace();
}
//User属性
public class User {
	private int id;
	private String username;
	private String password;
	private int sex;
	private String birthday ;
	private String email;
  ...
}
```
## 注解式实现文件上传
* 表单一定是**POST请求**
* 表单设置属性：**`enctype="multipart/form-data"`**
* 注解`@MultipartConfig`
* 图片写入硬盘需要绝对路径
  * `req.getServletContext().getRealPath("");`
* 图片写入mysql需要相对路径
  * `filename = "photos/"+new SimpleDateFormat("yy-MM-dd").format(Calendar.getInstance().getTime())+"/"+file;`
* 根据时间在photo下生成目录
* 根据时间重命名图片名


```html
<!-- type="file",method="post",enctype="multipart/form-data"-->
<form action="BookServlet.do?action=addbook" method="post" enctype="multipart/form-data">
书名：<input type="text" name="bname"><br/>
作者：<input type="text" name="bauthor"><br/>
价格：<input type="text" name="bprice"><br/>
出版日期：<input type="text" name="bdate" onfocus="WdatePicker({isShowClear:true,readOnly:true,skin:'blue'})"><br/>
图片：<input type="file" name="bimage"><br/>
是否上架：<input type="radio" name="bisonline" value="1">是<input type="radio" name="bisonline" value="0">否<br/>
<input type="submit" value="提交">
```

```java
private void book_add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//上传图片
		String filename = req.getParameter("bimage");

		try {
			Collection<Part> parts = req.getParts();
			for (Part p : parts) {
				String header = p.getHeader("content-disposition");
//						header
//						form-data; name="bname"
//						form-data; name="bauthor"
//						form-data; name="bprice"
//						form-data; name="bdate"
//						form-data; name="bimage"; filename="1.jpg"
//						form-data; name="bisonline"
				//找到图片文件
				if(header.contains("bimage")) {
					//从header中获取文件名
					String file = getFilenameByHeader(header);
					//创建目录
					File filedir = creatDir(req.getServletContext());
					//根据时间更新文件名
					file = newFilename(file);

					//完整文件
					File fileintodir = new File(filedir, file);
					System.out.println(fileintodir.getAbsolutePath());
					//将文件存入
					p.write(fileintodir.getAbsolutePath());
					//存入数据库为相对路径
					filename = "photos/"+new SimpleDateFormat("yy-MM-dd").format(Calendar.getInstance().getTime())+"/"+file;
					System.out.println(filename);
				}
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = bs.addbook(new Books(req.getParameter("bname"), req.getParameter("bauthor"), Double.parseDouble(req.getParameter("bprice")), req.getParameter("bdate"), filename, Integer.parseInt(req.getParameter("bisonline"))));
		if (count==1) {
			//添加成功->showall
			resp.sendRedirect("BookServlet.do?action=showall");
		}else {
			//添加失败->添加界面
			resp.sendRedirect("AddBook.jsp");
		}
	}
  	//根据时间更新文件名
	private String newFilename(String file) {
		return  new SimpleDateFormat("yyMMddhhmmss_").format(Calendar.getInstance().getTime())+file;
	}
	//创建一天一个的目录
	private File creatDir(ServletContext context) {
		String realPath = context.getRealPath("/photos");
		String date = new SimpleDateFormat("yy-MM-dd").format(Calendar.getInstance().getTime());
		File dir = new File(realPath, date);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
	private String getFilenameByHeader(String header) {
		//form-data; name="bimage"; filename="1.jpg"
		String[] arrys = header.split(";");//用;分割
		String filename = arrys[arrys.length-1];//最后一段filename="1.jpg"
		int start = filename.indexOf("\"");
		int end = filename.lastIndexOf("\"");
		return filename = filename.substring(start+1, end);//1.jpg
	}
```
## 注解实现修改图片

```java
package com.chen.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.chen.bean.Books;
import com.chen.servce.bookservce;
import com.chen.servce.bookservceimpl;

@WebServlet("/BookServlet.do")
@MultipartConfig
public class BookServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	bookservce bs = new bookservceimpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//统一编码格式
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		//获取行动参数
		String action = req.getParameter("action");
		if ("showall".equals(action)) {
			book_showall(req, resp);
		}else if ("delete".equals(action)) {
			book_delete(req, resp);
		}else if ("addbook".equals(action)) {
			book_add(req, resp);
		}else if ("update".equals(action)) {
			int bid = Integer.parseInt(req.getParameter("bid"));
			req.setAttribute("book", bs.getbookById(bid));
			req.getRequestDispatcher("update.jsp").forward(req, resp);
		}else if ("doupdate".equals(action)) {
			//更新
			String filename = req.getParameter("oldbimage");
			//获取parts
			Collection<Part> parts = req.getParts();
			for (Part p : parts) {
				String header = p.getHeader("content-disposition");
				System.out.println(header);
				if (header.contains("bimage")) {
//					form-data; name="bimage"; filename=""
					if (header.contains("filename=\"\"")) {
						break;
					}
					//从header中获取文件名
					String file = getFilenameByHeader(header);//1.jpg
					//创建目录
					//req.getServletContext().getRealPath("")为web项目的绝对路径
					File filedir = creatDir(req.getServletContext());//G:\Javaee1805\apache-tomcat-9.0.12\wtpwebapps\BookSystem\photos\18-09-20
					//根据时间更新文件名
					file = newFilename(file);//180920050440_1.jpg

					//完整文件路径
					//G:\Javaee1805\apache-tomcat-9.0.12\wtpwebapps\BookSystem\photos\18-09-20\180920050440_1.jp
					File fileintodir = new File(filedir, file);
					//将文件存入
					p.write(fileintodir.getAbsolutePath());
					//存入数据库为相对路径
					//photos/18-09-20/180920050440_1.jpg
					filename = "photos/"+new SimpleDateFormat("yy-MM-dd").format(Calendar.getInstance().getTime())+"/"+file;
				}
			}

			int count = bs.updatebook(new Books(Integer.parseInt(req.getParameter("bid")),req.getParameter("bname"), req.getParameter("bauthor"), Double.parseDouble(req.getParameter("bprice")), req.getParameter("bdate"), filename, Integer.parseInt(req.getParameter("bisonline"))));
			if (count==1) {
				//更新成功
				resp.getWriter().write("<script>alert('更新成功！');location.href='BookServlet.do?action=showall';</script>");
			}else {
				//更新失败
				resp.getWriter().write("<script>alert('更新失败！');location.href='BookServlet.do?action=showall';</script>");
			}
		}
	}
	private void book_add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//上传图片
		String filename = req.getParameter("bimage");
		try {
			Collection<Part> parts = req.getParts();
			for (Part p : parts) {
				String header = p.getHeader("content-disposition");
//						header
//						form-data; name="bname"
//						form-data; name="bauthor"
//						form-data; name="bprice"
//						form-data; name="bdate"
//						form-data; name="bimage"; filename="1.jpg"
//						form-data; name="bisonline"
				//找到图片文件
				if(header.contains("bimage")) {
					//从header中获取文件名
					String file = getFilenameByHeader(header);//1.jpg
					//创建目录
					//req.getServletContext().getRealPath("")为web项目的绝对路径
					File filedir = creatDir(req.getServletContext());//G:\Javaee1805\apache-tomcat-9.0.12\wtpwebapps\BookSystem\photos\18-09-20
					//根据时间更新文件名
					file = newFilename(file);//180920050440_1.jpg

					//完整文件路径
					//G:\Javaee1805\apache-tomcat-9.0.12\wtpwebapps\BookSystem\photos\18-09-20\180920050440_1.jp
					File fileintodir = new File(filedir, file);
					//将文件存入
					p.write(fileintodir.getAbsolutePath());
					//存入数据库为相对路径
					//photos/18-09-20/180920050440_1.jpg
					filename = "photos/"+new SimpleDateFormat("yy-MM-dd").format(Calendar.getInstance().getTime())+"/"+file;
				}
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = bs.addbook(new Books(req.getParameter("bname"), req.getParameter("bauthor"), Double.parseDouble(req.getParameter("bprice")), req.getParameter("bdate"), filename, Integer.parseInt(req.getParameter("bisonline"))));
		if (count==1) {
			//添加成功->showall
			resp.sendRedirect("BookServlet.do?action=showall");
		}else {
			//添加失败->添加界面
			resp.sendRedirect("AddBook.jsp");
		}
	}
	//根据时间更新文件名
	private String newFilename(String file) {
		return  new SimpleDateFormat("yyMMddhhmmss_").format(Calendar.getInstance().getTime())+file;
	}
	//创建一天一个的目录
	private File creatDir(ServletContext context) {
		//req.getServletContext().getRealPath("")为web项目的绝对路径
		String realPath = context.getRealPath("/photos");//G:\Javaee1805\apache-tomcat-9.0.12\wtpwebapps\BookSystem\photos
		String date = new SimpleDateFormat("yy-MM-dd").format(Calendar.getInstance().getTime());
		File dir = new File(realPath, date);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
	private String getFilenameByHeader(String header) {
		//form-data; name="bimage"; filename="1.jpg"
		String[] arrys = header.split(";");//用;分割
		String filename = arrys[arrys.length-1];//最后一段filename="1.jpg"
		int start = filename.indexOf("\"");
		int end = filename.lastIndexOf("\"");
		return filename = filename.substring(start+1, end);//1.jpg
	}
	private void book_delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int bid = Integer.parseInt(req.getParameter("bid"));
		int count =bs.deletebook(bid);
		if (count ==1) {
			//删除成功，再次查询跳转
			resp.getWriter().write("<script>alert('删除成功！');location.href='BookServlet.do?action=showall';</script>");
		}else {
			//TODO 删除失败
		}
	}
	private void book_showall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//将数据转发到books.jsp
		req.setAttribute("booklist", bs.getbooks());
		req.getRequestDispatcher("Books.jsp").forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
```

## 生成验证码
