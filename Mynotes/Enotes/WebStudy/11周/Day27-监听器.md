# 监听器
* 监听器用于监听web应用中某些对象、信息的创建、销毁、增加，修改，删除等动作的发生，然后作出相应的响应处理。
* 监听的对象
  * ServletContext 整个应用
  * Session 一次会话
  * request 当前请求
  * PageContext 当前页面有效

## ServletContextListener
ServletContextListener接口用于监听ServletContext对象的创建和销毁事件。实现了ServletContextListener接口的类都可以对ServletContext对象的创建和销毁进行监听。
　当ServletContext对象被创建时，激发contextInitialized (ServletContextEvent sce)方法。
　当ServletContext对象被销毁时，激发contextDestroyed(ServletContextEvent sce)方法。
　ServletContext域对象创建和销毁时机：
　　　　创建：服务器启动针对每一个Web应用创建ServletContext对象！
　　　　销毁：服务器关闭前先关闭代表每一个web应用的ServletContext！

范例：编写一个MyServletContextListener类，实现ServletContextListener接口，监听ServletContext对象的创建和销毁

```java
public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContext 出生了....");
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// ServletContext销毁时调用的方法
		System.out.println("ServletContext 升天了....");
	}
}
```

## 在web.xml文件中注册监听器
要想监听事件源，那么必须将监听器注册到事件源上才能够实现对事件源的行为动作进行监听，在JavaWeb中，监听的注册是在web.xml文件中进行配置的，如下：
```xml
	<!-- 注册针对ServletContext对象进行监听的监听器 -->
	<listener>
		<description>ServletContextListener监听器</description>
		<!--实现了ServletContextListener接口的监听器类 -->
	<listener-class>com.bruceliu.filter.MyServletContextListener</listener-class>
	</listener>
```
注解式配置
```java
@WebListener
public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContext 出生了....");
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// ServletContext销毁时调用的方法
		System.out.println("ServletContext 升天了....");
	}
}
```

## 案例1：任务调度（使用 Timer和TimerTask）
```java
@WebListener
public class TimerListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("发邮件....");
			}
		}, 5000, 2000);
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
```

## HttpSessionListener
HttpSessionListener 接口用于监听HttpSession对象的创建和销毁
　　创建一个Session时，激发sessionCreated (HttpSessionEvent se) 方法
销毁一个Session时，激发sessionDestroyed (HttpSessionEvent se) 方法。

HttpSession对象 创建的时机：第一次访问的时候创建session对象 使用不同的浏览器，会创建不同的session对象

HttpSession对象 销毁的时机：你session在服务器中有效期到了之后销毁

范例：编写一个MyHttpSessionListener类，实现HttpSessionListener接口，监听HttpSession对象的创建和销毁
```java
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// arg0.getSession(); 获取事件源
		System.out.println("Session创建了<<<<<");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Session销毁<<<<<");
	}
}
```

HttpSession的销毁时机需要在web.xml中进行配置，如下：
```xml
	<session-config>
		<session-timeout>1</session-timeout>
	</session-config>
```
当我们访问jsp页面时，HttpSession对象就会创建，此时就可以在HttpSessionListener观察到HttpSession对象的创建过程了，我们可以写一个jsp页面观察HttpSession对象创建的过程。
如下：index.jsp
```xml
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>HttpSessionListener监听器监听HttpSession对象的创建</title>
</head>
<body>
	session已经创建对象：${pageContext.session.id}<br/>
	<%=session.getId()%>
</body>
</html>
```
## ServletRequestListener
ServletRequestListener接口用于监听ServletRequest 对象的创建和销毁
Request对象被创建时，监听器的requestInitialized(ServletRequestEvent sre)方法将会被调用
Request对象被销毁时，监听器的requestDestroyed(ServletRequestEvent sre)方法将会被调用
　　ServletRequest域对象创建和销毁时机：
　　　　创建：用户每一次访问都会创建request对象
　　　　销毁：当前访问结束，request对象就会销毁
范例：编写一个MyServletRequestListener类，实现ServletRequestListener接口，监听ServletRequest对象的创建和销毁
```java
@WebListener
public class MyRequestListener implements ServletRequestListener {

	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("request创建了###");
	}

	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("request销毁了###");
	}
}
```
## 案例2: 统计当前在线人数（ServletContextListener HttpSessionListener）
```java
@WebListener
public class OnlineScListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext sc = arg0.getServletContext();
		sc.setAttribute("count", 0);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
```
```java
@WebListener
public class OnlineSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		ServletContext sc = event.getSession().getServletContext();
		int count = (Integer) sc.getAttribute("count");
		// count++;
		sc.setAttribute("count", ++count);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		ServletContext sc = event.getSession().getServletContext();
		int count = (Integer) sc.getAttribute("count");
		sc.setAttribute("count", --count);
	}
}
```
```xml
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>首页</title>
</head>
<body>
	<h1>当前在线人数${count}</h1>
</body>
</html>
```

## 第二类：监听值变化
域对象中属性的变更的事件监听器就是用来监听 ServletContext, HttpSession, HttpServletRequest 这三个对象中的属性变更信息事件的监听器。
　　这三个监听器接口分别是ServletContextAttributeListener, HttpSessionAttributeListener 和ServletRequestAttributeListener，这三个接口中都定义了三个方法来处理被监听对象中的属性的增加，删除和替换的事件，同一个事件在这三个接口中对应的方法名称完全相同，只是接受的参数类型不同。
3.1ServletContextAttributeListener
编写ServletContextAttributeListener监听器监听ServletContext域对象的属性值变化情况，代码如下：
```java
@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		String str = MessageFormat.format("ServletContext域对象中添加了属性:{0}，属性值是:{1}", scab.getName(), scab.getValue());
		System.out.println(str);
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		String str = MessageFormat.format("ServletContext域对象中删除属性:{0}，属性值是:{1}", scab.getName(), scab.getValue());
		System.out.println(str);
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		String str = MessageFormat.format("ServletContext域对象中替换了属性:{0}的值", scab.getName());
		System.out.println(str);
	}
}
```
编写test.jsp测试页面
```xml
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>ServletContextAttributeListener监听器测试</title>
</head>

<body>
	<%
		//往application域对象中添加属性
		application.setAttribute("name", "哈哈");
		//替换application域对象中name属性的值
		application.setAttribute("name", "呵呵");

		//移除application域对象中name属性
		application.removeAttribute("name");
	%>
</body>
</html>
```
## HttpSessionAttributeListener
```java
@WebListener
public class MySessionAttrListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		String str = MessageFormat.format("HttpSession域对象中添加了属性:{0}，属性值是:{1}", se.getName(), se.getValue());
		System.out.println(str);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String str = MessageFormat.format("HttpSession域对象中删除属性:{0}，属性值是:{1}", se.getName(), se.getValue());
		System.out.println(str);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		String str = MessageFormat.format("HttpSession域对象中替换了属性:{0}的值", se.getName());
		System.out.println(str);
	}
}
```
编写test1.jsp测试页面
```xml
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>ServletContextAttributeListener监听器测试</title>
</head>

<body>
	<%
		//往session域对象中添加属性
		session.setAttribute("aa", "bb");
		//替换session域对象中aa属性的值
		session.setAttribute("aa", "xx");
		//移除session域对象中aa属性
		session.removeAttribute("aa");
	%>
</body>
</html>
```
## ServletRequestAttributeListener
```java
@WebListener
public class MyRequestAttributeListener implements ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		String str = MessageFormat.format("ServletRequest域对象中添加了属性:{0}，属性值是:{1}", srae.getName(), srae.getValue());
		System.out.println(str);
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		String str = MessageFormat.format("ServletRequest域对象中删除属性:{0}，属性值是:{1}", srae.getName(), srae.getValue());
		System.out.println(str);
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		String str = MessageFormat.format("ServletRequest域对象中替换了属性:{0}的值", srae.getName());
		System.out.println(str);
	}
}
```
编写test2.jsp测试页面
```xml
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>ServletContextAttributeListener监听器测试</title>
</head>

<body>
	<%
		//往request域对象中添加属性
		request.setAttribute("aa", "bb");
		//替换request域对象中aa属性的值
		request.setAttribute("aa", "xx");
		//移除request域对象中aa属性
		request.removeAttribute("aa");
	%>
</body>
</html>
```
## 第三类：监听HttpSession中的对象的（JavaBean）
前两类监听器是作用在 ServletContext HttpSession ServletRequest上的，第三类监听器是作用在JavaBean上的。
这类监听器不需要在web.xml中配置。
## HttpSessionBindingListener
实现了HttpSessionBindingListener接口的JavaBean对象可以感知自己被绑定到Session中和 Session中删除的事件
　　当对象被绑定到HttpSession对象中时，web服务器调用该对象的void valueBound(HttpSessionBindingEvent event)方法
　　当对象从HttpSession对象中解除绑定时，web服务器调用该对象的void valueUnbound(HttpSessionBindingEvent event)方法
```java
public class User implements HttpSessionBindingListener {

	private int id;
	private String name;

	public User() {

	}

	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("对象绑定到了Session中");
	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("对象从Session中移除");
	}
}
```
测试页面
```xml
<%@ page import="com.bruceliu.bean.User"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>ServletContextAttributeListener监听器测试</title>
</head>

<body>
	<%
		User user = new User(1, "aaa");
		session.setAttribute("user", user);
		session.removeAttribute("user");
	%>
</body>
</html>
```
## HttpSessionActivationListener
**Session的钝化和活化**

实现了HttpSessionActivationListener接口的JavaBean对象可以感知自己被活化(反序列化)和钝化(序列化)的事件
当绑定到HttpSession对象中的javabean对象将要随HttpSession对象被钝化(序列化)之前，web服务器调用该javabean对象的void sessionWillPassivate(HttpSessionEvent event) 方法。这样javabean对象就可以知道自己将要和HttpSession对象一起被序列化(钝化)到硬盘中.
当绑定到HttpSession对象中的javabean对象将要随HttpSession对象被活化(反序列化)之后，web服务器调用该javabean对象的void sessionDidActive(HttpSessionEvent event)方法。这样javabean对象就可以知道自己将要和 HttpSession对象一起被反序列化(活化)回到内存中
放在Session中的 没有实现Serilizable接口的对象，在Session钝化时，不会被序列化到磁盘上。
```java
public class Person implements Serializable, HttpSessionActivationListener {
	private int id;
	private String name;

	public Person() {

	}

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void sessionDidActivate(HttpSessionEvent arg0) {
		// 活化
		System.out.println("对象被活化......");
	}

	public void sessionWillPassivate(HttpSessionEvent arg0) {
		// 钝化
		System.out.println("对象被钝化.......");
	}
}
```

为了观察绑定到HttpSession对象中的javabean对象随HttpSession对象一起被钝化到硬盘上和从硬盘上重新活化回到内存中的的过程，我们需要借助tomcat服务器帮助我们完成HttpSession对象的钝化和活化过程，具体做法如下：

在WebRoot\META-INF文件夹下创建一个context.xml文件，如下所示：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context>
	<!-- maxIdleSwap:1 session如果1分钟没有使用 就序列化. directory:序列化后 文件所保存的路径. -->
	<Manager className="org.apache.catalina.session.PersistentManager"
		maxIdleSwap="1">
		<Store className="org.apache.catalina.session.FileStore" directory="bruce123" />
	</Manager>
</Context>
```
在context.xml文件文件中配置了1分钟之后就将HttpSession对象钝化到本地硬盘的一个bruce123文件夹中

jsp测试代码如下：
```xml
<%@page import="com.bruceliu.bean.Person"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>测试</title>
</head>

<body>
	<%
		Person p = new Person(1, "zhangsan");
		session.setAttribute("p", p);
	%>
</body>
</html>
```
