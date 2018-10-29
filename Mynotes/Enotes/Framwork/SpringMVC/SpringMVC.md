# SpringMVC

## SpringMVC基本组件

* DispatcherServlet 前段控制器，--WEB.xml配置
* Controller 处理器，页面控制器
* HandlerMapping 请求映射处理器
* ViewResolver 视图解析器


## 配置SpringMVC

### 通过Maven导包
```xml
        <dependency>
            <!-- Junit测试 -->
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>
        <!-- 添加Spring包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>4.3.8.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.3.8.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>4.3.8.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>4.3.8.RELEASE</version>
        </dependency>


        <!-- 为了方便进行单元测试，添加spring-test包 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>4.3.8.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>4.3.8.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>4.3.8.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-orm</artifactId>
            <version>4.3.8.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.5</version>
        </dependency>
        <!-- jstl -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <!-- Hiberante核心包 -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.3.3.Final</version>
        </dependency>
        <!-- mysql驱动包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.12</version>
        </dependency>
    </dependencies>
```

### 配置web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <welcome-file-list>
        <welcome-file>home.jsp</welcome-file>
    </welcome-file-list>
    <!--配置SpringMVC核心控制器-->
    <servlet>
        <servlet-name>springDispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--配置DispatcherServlet初始化参数，设置文件路径和文件名-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springDispatcherServlet</servlet-name>
        <!-- 不能写/*，必须写/，这是REST URL风格的要求，REST风格会在后面介绍 -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <!-- 配置POST乱码问题 -->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

### 配置Springmvcxml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:bean="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/bean
        ">
    <!--扫描controller-->
    <context:component-scan base-package="com.hibian.controller"/>
    <!--视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!--jsp所在位置-->
        <property name="prefix" value="/WEN-INF/jsp/"/>
        <!--文件后缀-->
        <property name="suffix" value=".jsp"/>
    </bean>

    <mvc:annotation-driven />
    <!-- 静态资源放行  放行static文件夹下的资源 -->
    <mvc:resources location="/static/" mapping="/static/**" />

</beans>
```

## Demo

```java
package com.hibian.controller;

import com.hibian.bean.Emp;
import com.hibian.servce.empservce;
import com.hibian.servce.empserveceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/test")
public class testSpringmvc {

     empservce es = new empserveceimpl();

    @RequestMapping("/test1")
    public String test1(Model model){
        model.addAttribute("msg","time is quick");
        return "hello";
    }
    @RequestMapping("/index")
    public String index(String name,Model model){
        if (name==null){
            name="";
        }
        List<Emp> emps = es.getlist(name);
        model.addAttribute("emps",emps);
        return "index";
    }
    @RequestMapping("/add")
    public String add(Emp u){
        int renum = es.add(u);
        if (renum==1){
            return "redirect:/test/index";
        }else {
            return "redirect:/test/add";
        }
    }
    @RequestMapping("/del/{id}")
    public void del(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        int renum = es.del(Integer.valueOf(id));
        response.setContentType("text/html;charset=utf-8");
        if (renum==1){
            response.getWriter().write("<script>alert(\"删除成功！\");location.href='/test/index';</script>");
        }else {
            response.getWriter().write("<script>alert(\"删除失败！\");location.href='/test/index';</script>");
        }
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id")String id, HttpSession session){
        Emp emp = es.getEmpById(Integer.valueOf(id));
        session.setAttribute("emp",emp);
        return "edit";
    }
    @RequestMapping(value = "/realedit",method = RequestMethod.POST)
    public String realedit(Emp e){
        int renum = es.updateEmp(e);
        if (renum==1){
            return "redirect:/test/index";
        }else {
            return  "redirect:/test/edit/"+e.getId();
        }
    }
    @RequestMapping("/delall/{ids}")
    public void delall(@PathVariable("ids") String ids,HttpServletResponse response) throws IOException {
        int renum = es.delall(ids);
        if (renum==1){
            response.getWriter().write("<script>location.href=\"/test/index\"</script>");
        }
        response.getWriter().write("<script>alert(\"删除失败！！\");location.href=\"/test/index\"</script>");
    }
}
```
```html
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/29
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/Style/skin.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/Js/jquery-1.8.3.min.js"></script>

    <script>
        $(function () {
           $("#ckz") .click(function () {
               $("input[name='ck']").prop("checked",this.checked);
           });
           $("#deleteAll").click(function () {
               var  chk_list =[];
               $("#content_table").find("input[name='ck']:checked").each(function () {
                   chk_list.push($(this).val());
               })
               if (confirm("确认要删除选中元素吗？")){
                   location.href="${pageContext.request.contextPath}/test/delall/"+chk_list;
               }
           });
        });
        function del(id) {
            if (confirm("确认要删除吗？")) {
                location.href="/test/del/"+id;
            }
        }
    </script>
    <title>Title</title>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <!-- 头部开始 -->
    <tr>
    <tr>
        <td width="17" valign="top" background="${pageContext.request.contextPath}/static/Images/mail_left_bg.gif">
            <img src="${pageContext.request.contextPath}/static/Images/left_top_right.gif" width="17" height="29" />
        </td>
        <td valign="top" background="${pageContext.request.contextPath}/static/Images/content_bg.gif">
            <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" background="${pageContext.request.contextPath}/static/Images/content_bg.gif">
                <tr><td height="31"><div class="title">用户管理</div></td></tr>
            </table>
        </td>
        <td width="16" valign="top" background="${pageContext.request.contextPath}/static/Images/mail_right_bg.gif"><img src="${pageContext.request.contextPath}/static/Images/nav_right_bg.gif" width="16" height="29" /></td>
    </tr>
    <!-- 中间部分开始 -->
        <!--第一行左边框-->
        <td valign="middle" background="${pageContext.request.contextPath}/static/Images/mail_left_bg.gif">&nbsp;</td>
        <!--第一行中间内容-->
        <td valign="top" bgcolor="#F7F8F9">
            <table style="width:100%" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <!-- 空白行-->
                <tr><td colspan="2" valign="top">&nbsp;</td><td>&nbsp;</td><td valign="top">&nbsp;</td></tr>
                <tr>
                    <td colspan="4">
                        <form class="clearfix" action="/test/index" method="post">
                            <div style="float:left">
                                <p style="float:left; margin-left:30px;"><label>姓名：</label><input class="text" name="name" list="rolelist" style="width:80px;"/></p>&nbsp;&nbsp;&nbsp;

                                <p style="float:left; margin-left:30px;"><input type="submit" class="btn" value="查询"/></p>
                            </div>
                            <div style="float:right">
                                <input type="button" onclick='window.location="${pageContext.request.contextPath}/WEN-INF/jsp/add.jsp"' class="btn" value="添加"/> &nbsp;&nbsp;&nbsp;
                                <input type="button" id="deleteAll" class="btn" value="批量删除"/>
                            </div>
                        </form>
                        <datalist id="rolelist">
                            <option></option>
                            <option></option>

                        </datalist>
                    <td>
                </tr>
                <!-- 一条线 -->
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr><td></td></tr>
                        </table>
                    </td>
                </tr>
                <!-- 列表展示开始 -->
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="" method="">
                                        <table width="100%" id="content_table" class="cont tr_color">
                                            <tr>
                                                <th><input type="checkbox" value="" id="ckz"/></th>
                                                <th>工号</th>
                                                <th>姓名</th>
                                                <th>性别</th>
                                                <th>年龄</th>
                                                <th>身份证号</th>
                                                <th>电话</th>
                                                <th>地址</th>
                                                <th>入职时间</th>
                                                <th>离职时间</th>
                                                <th>状态</th>
                                                <th>操作</th>
                                            </tr>
                                            <c:forEach var="e" items="${emps}">
                                            <tr align="center" class="d">
                                                <td><input type="checkbox" name="ck" value="${e.id}" class="cks"/></td>
                                                <td>${e.id}</td>
                                                <td>${e.name}</td>
                                                <td>${e.sex}</td>
                                                <td>${e.age}</td>
                                                <td>${e.idCard}</td>
                                                <td>${e.phone}</td>
                                                <td>${e.address}</td></td>
                                                <td>${e.entryTime}</td>
                                                <td>${e.quitTime}</td>
                                                <td>${e.state==1?"启用":"禁用"}</td>
                                                <td><a href="/test/edit/${e.id}">修改</a>&nbsp;&nbsp;
                                                    <a href="javascript:void(0)" onclick="del(${e.id})">删除</a></td>
                                            </tr>
                                            </c:forEach>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>
                <!--列表展示结束 -->
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr><td></td></tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
        <td background="${pageContext.request.contextPath}/static/Images/mail_right_bg.gif">&nbsp;</td>
    </tr>
    <!-- 底部部分 -->
    <tr>
        <td valign="bottom" background="${pageContext.request.contextPath}/static/Images/mail_left_bg.gif">
            <img src="${pageContext.request.contextPath}/static/Images/buttom_left.gif" width="17" height="17" />
        </td>
        <td background="${pageContext.request.contextPath}/static/Images/buttom_bgs.gif">
            <img src="${pageContext.request.contextPath}/static/Images/buttom_bgs.gif" width="17" height="17">
        </td>
        <td valign="bottom" background="${pageContext.request.contextPath}/static/Images/mail_right_bg.gif">
            <img src="${pageContext.request.contextPath}/static/Images/buttom_right.gif" width="16" height="17" />
        </td>
    </tr>
</table>
</body>
</html>

```
