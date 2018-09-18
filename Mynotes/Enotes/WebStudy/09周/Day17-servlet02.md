[toc]
# servlet使用和跳转
## 使用注解配置servlet(3.0以后)
除了使用web.xml可以配置servlet还可以使用注解
```java
//@WebServlet(value = "/test01.do", loadOnStartup = 1)
@WebServlet("/test01.do")
public class test01 extends HttpServlet{
  ...
}
```
ps:loadOnStartup在**服务器启动时**创建类并且init(),数字越小，越先起作用

## servlet获取请求参数
使用`setCharacterEncoding("UTF-8");`设置编码格式

使用`req.getParameter("username");`获取网页中的值

```java
//@WebServlet(value = "/hello", loadOnStartup = 1)
@WebServlet("/test01.do")
public class test01 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String userpass = req.getParameter("userpass");
		System.out.println(username+"\t"+userpass);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}

```

## 请求方式GET/POST
* GET请求
  * 参数传递方式:在浏览器地址栏传递参数,**用?分割url和要传输的数据,参数之间用&连接**
  * 数据量限制:大小**有限制**,url长度有限制
  * 安全问题:地址栏会显示数据内容
  * 乱码处理:**每个参数都要处理**
    * `name=new String(name.getBytes("IOS8859-1"),"UTF-8");`
  * 优点：效率高，适合传递少量不敏感的数据
* POST请求
  * 参数传递方式:将数据放在**HTTP包的Body中**
  * 数据量限制:大小没有限制,文件上传必须为POST
  * 安全问题:相对安全
  * 乱码处理:统一设置
    * `setCharacterEncoding("UTF-8");`

## servlet输出中文
设置浏览器响应的编码
`resp.setContentType("text/html;charset=utf-8");`

设置获取参数的编码
`req.setCharacterEncoding("UTF-8");`

```java
@WebServlet("/test01.do")
public class test01 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");//设置获取参数的编码格式
		resp.setContentType("text/html;charset=utf-8");//设置浏览器响应的编码格式
		String username = req.getParameter("username");
		String userpass = req.getParameter("userpass");
		PrintWriter writer = resp.getWriter();
		writer.write(username);
		writer.write(userpass);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
```
## servlet跳转和路径
### 重定向
* 浏览器**访问**url
* url响应码**302**，告诉浏览器需要重定向
* 设置重定向的url
* 浏览器**访问**重定向的url

```java
resp.setStatus(302);
resp.setHeader("Location", "http://www.baidu.com");
//或者一句代码
resp.sendRedirect("1.jsp");
```
使用重定向req携带的信息会丢失

### 转发
* 浏览器访问url
* 服务器转发请求到其他url,获取url返回的resp(必须是同一个web容器下的url)
* 浏览器获得返回的resp

```java
request.getRequestDispatcher("index.jsp").forward(request, response);
```

### servlet路径

Servlet中，"/"代表Web应用的跟目录。和物理路径的相对表示。例如："./" 代表当前目录,
"../"代表上级目录。这种类似的表示，也是属于相对路径.

服务器端的相对地址指的是相对于你的web应用的地址，这个地址是在服务器端解析的（不同于html和javascript中的相对地址，他们是由客户端浏览器解析的）也就是说这时候在jsp和servlet中的相对地址应该是相对于你的web应用，即相对于`http://127.0.0.1:8080/test/`的
