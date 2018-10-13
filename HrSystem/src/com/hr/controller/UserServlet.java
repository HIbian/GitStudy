package com.hr.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.hr.bean.User;
import com.hr.servce.UserServce;
import com.hr.servce.UserServceImpl;
import com.hr.utils.pageHelper;
@WebServlet("/UserServlet.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserServce us = new UserServceImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//获取需要执行的方法
		String action = request.getParameter("action");
		if ("index".equals(action)) {
			queryPage(request, response);//查询页面
		}else if ("addUser".equals(action)) {//添加用户
			String userLoginName = request.getParameter("userLoginName");
			String userName = request.getParameter("userName");
			String userPwd = request.getParameter("userPwd");
			String rePwd = request.getParameter("rePwd");
			if (userPwd==null) {
				response.sendRedirect("UserServlet.do?action=addUser");
				return;
			}
			if (!userPwd.equals(rePwd)) {//两次输入不一样
				response.sendRedirect("UserServlet.do?action=addUser");
				return;
			}
			User u = new User(userLoginName, userPwd, userName, 1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 0);
			int count = us.addUser(u);
			if (count==1) {//添加成功
				response.sendRedirect("UserServlet.do?action=index");
			}else {//添加失败
				response.sendRedirect("UserServlet.do?action=addUser");
			}
			
		}else if ("updateUser".equals(action)) {//获取需要跟新的对象和数据，数据回显
			updateUser(request,response);
		}else if ("update".equals(action)) {//真正的更新
			update(request, response);
		}else if ("delete".equals(action)) {//删除
			delete(request, response);
		}else if ("login".equals(action)) {//用户登陆
			login(request, response);
		} else if ("logout".equals(action)) {//用户注销
			HttpSession session = request.getSession();
			session.removeAttribute("loginUser");
			response.sendRedirect("login.jsp");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取账号和密码，验证码
		String userLoginName = request.getParameter("userLoginName");
		String userPwd = request.getParameter("userPwd");
		String userCode = request.getParameter("userCode");

		//获取系统生成的验证码
		String code = ((StringBuffer)request.getSession().getAttribute("code")).toString();
		//判断验证码是否正确
		if (!code.equals(userCode)) {
			request.setAttribute("message", "验证码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//判断账号密码是否正确
		User u= us.userLogin(userLoginName,userPwd);
		if (u!=null) {
			System.out.println("登陆成功");
			//设置session
			request.getSession().setAttribute("loginUser", u);
			response.sendRedirect("main.jsp");
		}else {
			System.out.println("登陆失败");
			request.setAttribute("message", "账号或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userIds = request.getParameter("userIds");//userIds like 3,4,5
		System.out.println(userIds);
		String[] split = userIds.split(",");
		int[] userIds_int = new int[split.length];
		for (int i = 0; i < split.length; i++) {
			userIds_int[i] = Integer.parseInt(split[i]);//String转换为int
			
			us.deleteUserById(userIds_int[i]);//删除,返回影响行数
		}
		response.sendRedirect("UserServlet.do?action=index");
	}
	private void updateUser(HttpServletRequest request,HttpServletResponse response){
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = us.getUserById(userId);
		request.setAttribute("user", user);//数据回显
		try {
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User u = new User();
		try {
			BeanUtils.populate(u, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int count = us.updateUser(u);
		if (count==1) {
			response.sendRedirect("UserServlet.do?action=index");
		}else {
			//更新失败
		}
	}
	private void queryPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName")==null?"":request.getParameter("userName");//第一次进入index时，为null
		int state = request.getParameter("state")==null?-1:Integer.parseInt(request.getParameter("state"));
		//获取页码，第一次进入为null，需要设置初始值
		int pageIndex = request.getParameter("indexPage")==null?1:Integer.parseInt( request.getParameter("indexPage"));
		//设置查询条件
		User userT = new User(userName, state);
		//设置分页每一页大小
		int pageSize = 5;
		//获取查询结果
		int totalCount = us.getTotalCount(userT);
		List<User> userPage = us.getUserPage(userT, pageIndex, pageSize);
		//封装为对象传递给jsp,需要做数据回显，需要传入的值有userT，pageIndex，pageSize，totalCount，userPage
		pageHelper ph = new pageHelper(userT, pageIndex, pageSize, totalCount, userPage);
		//+++获取页码按钮信息
		int[] pageIndexList = getPageIndexList(pageIndex, pageSize, totalCount);
		//设置attribute并传入jsp中
		request.setAttribute("ph", ph);
		request.setAttribute("pageIndexList", pageIndexList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	//获取页码列表
	private int[] getPageIndexList(int pageIndex,int pageSize,int totalCount) {
		int maxPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize+1);
		if (maxPage<=10) {
			int[] pageIndexList = new int[maxPage];
			for (int i = 0; i <maxPage; i++) {
				pageIndexList[i] = i+1;
			}
			return pageIndexList;
		}
		int[] pageindexList = new int[10];
		if (pageIndex<=5) {
			for (int i = 0; i < pageindexList.length; i++) {
				pageindexList[i] = i+1;
			}
			return pageindexList;
		}
		if (maxPage-pageIndex<=5) {
			for (int i = 0; i < pageindexList.length; i++) {
				pageindexList[i] = maxPage-pageindexList.length+1+i;
			}
			return pageindexList;
		}
		for (int i = 0; i < pageindexList.length; i++) {
			pageindexList[i] = pageIndex-5+i;
		}
		return pageindexList;
	}
}
