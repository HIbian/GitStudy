[toc]
# Servlet开发(一)
## 运行一个servlet
* 编写一个继承**HttpServlet**的类,编译为.class文件
* 将.class文件放入**webroot(webcontent)/WEB-INF/classes**中
* 在**webroot(webcontent)/WEB-INF/web.xml**中注册servlet
* 根据web.xml中的**url-pattern**运行servlet

```java
package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class test07 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servlet_info = request.getHeader("Accept");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		writer.println("<html>");
		writer.println("<head><title>com.test.test07</title></head>");

		writer.println("<body>");
		writer.println(servlet_info);
		writer.println("</body>");
		writer.println("</html>");
		writer.flush();
		writer.close();
	}
}
```
## Servlet访问URL映射配置
通过配置web.xml,一个servlet可以映射多个地址
```xml
  <servlet>
    <servlet-name>test07</servlet-name>
    <servlet-class>com.test.test07</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>test07</servlet-name>
    <url-pattern>/test07.do</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>test07</servlet-name>
    <url-pattern>/index.html</url-pattern>
  </servlet-mapping>
```
## Servlet访问URL使用*通配符映射
* `<url-pattern>/*.jsp</url-pattern>`
* `<url-pattern>/文件夹/文件夹/*</url-pattern>`

```
对于如下的一些映射关系：
　　Servlet1 映射到 /abc/* 
　　Servlet2 映射到 /* 
　　Servlet3 映射到 /abc 
　　Servlet4 映射到 *.do 
问题：
　　当请求URL为“/abc/a.html”，“/abc/*”和“/*”都匹配，哪个servlet响应
    　　Servlet引擎将调用Servlet1。
　　当请求URL为“/abc”时，“/abc/*”和“/abc”都匹配，哪个servlet响应
    　　Servlet引擎将调用Servlet3。
　　当请求URL为“/abc/a.do”时，“/abc/*”和“*.do”都匹配，哪个servlet响应
    　　Servlet引擎将调用Servlet1。
　　当请求URL为“/a.do”时，“/*”和“*.do”都匹配，哪个servlet响应
    　　Servlet引擎将调用Servlet2。
　　当请求URL为“/xxx/yyy/a.do”时，“/*”和“*.do”都匹配，哪个servlet响应
    　　Servlet引擎将调用Servlet2。
　匹配的原则就是"谁长得更像就找谁"
```

## `<load-on-startup>`
如果在<servlet>元素中配置了一个<load-on-startup>元素，那么WEB应用程序在启动时，就会装载并创建Servlet的实例对象、以及调用Servlet实例对象的init()方法。
举例：
```xml
    <servlet>
        <servlet-name>invoker</servlet-name>
        <servlet-class>
            org.apache.catalina.servlets.InvokerServlet
        </servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
```
用途：为web应用写一个InitServlet，这个servlet配置为启动时装载，为整个web应用创建必要的数据库表和数据。

## 缺省Servlet

映射路径仅仅为一个正斜杠（/）

凡是在web.xml文件中找不到匹配的`<servlet-mapping>`元素的URL，它们的访问请求都将交给缺省Servlet处理
```java
package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//却省Servlet
public class test06 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		writer.println("<html>");
		writer.println("<head><title>com.test.test07</title></head>");

		writer.println("<body>");
		writer.println("<h1>404 NOT FIND</h1>");
		writer.println("</body>");
		writer.println("</html>");
		writer.flush();
		writer.close();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}

```

## Servlet的线程安全问题
当**多个客户端**并发访问同一个Servlet时，web服务器会为**每一个客户端**的访问请求创建一个线程，并在这个线程上调用Servlet的service方法，因此service方法内如果访问了**同一个资源**的话，就有可能引发**线程安全问题**。

写在dopost或者doget方法内的变量不会有线程安全问题，因为每个对象独享一份变量

变量写在dopost等方法体外时，也就是说作为servlet的属性时，会引发线程安全问题

可以通过加锁的方式解决，但在实际应用中，若有9999人访问，就会有9999人排队，这显然是不能解决问题的。

通过实现SingleThreadModel接口(标记接口)可以使servlet创建多个实例对象，每个线程独立使用实例对象。(被标记为过时的)
