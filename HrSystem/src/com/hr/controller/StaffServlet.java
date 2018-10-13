package com.hr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.bean.Staff;
import com.hr.servce.StaffServce;
import com.hr.servce.StaffServceimpl;
import com.hr.utils.PageHelperT;

@WebServlet("/StaffServlet.do")
public class StaffServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	StaffServce ss = new StaffServceimpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("query".equals(action)) {//执行条件查询
			query(req, resp);
		}else if ("update".equals(action)) {//数据回显到更改框
			update(req, resp);
		}else if ("realupdate".equals(action)) {//更改数据库中的数据
			realupdate(req, resp);
		}else if ("deleteStaff".equals(action)) {//删除职位，真删
			deleteStaff(req, resp);
		}
	}
	private void deleteStaff(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String delstr = req.getParameter("delstr");
		String[] split = delstr.split(",");
		for (String s : split) {
			ss.deleteStaffById(Integer.parseInt(s));
		}
		resp.sendRedirect("StaffServlet.do?action=query");
	}
	private void realupdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int staffId = Integer.parseInt(req.getParameter("staffId"));
		String staffName = req.getParameter("staffName");
		String staffDes = req.getParameter("staffDes");
		Staff staff = new Staff(staffId,staffName, staffDes);
		int count = ss.updateStaff(staff);
		if (count!=-1) {
			System.out.println("更新成功");
			resp.sendRedirect("StaffServlet.do?action=query");
		}else {
			//更新失败
		}
	}
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int staffId = Integer.parseInt(req.getParameter("staffId"));
		Staff staff =  ss.getStaffById(staffId);
		//获取。。
		req.setAttribute("staff", staff);
		req.getRequestDispatcher("UpdataStaff.jsp").forward(req, resp);
	}
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String staffId = (req.getParameter("staffId")==null||req.getParameter("staffId")=="")?"-1":req.getParameter("staffId");
		String staffName = req.getParameter("staffName")==null?"":req.getParameter("staffName");
		int indexPage = req.getParameter("indexPage")==null?1:Integer.parseInt(req.getParameter("indexPage"));
		int pageSize = req.getParameter("pageSize")==null?10:Integer.parseInt(req.getParameter("pageSize"));
		System.out.println(staffId+"--"+staffName+"--"+indexPage+"--"+pageSize);
		Staff staff = new Staff(Integer.parseInt(staffId), staffName);//条件
		int totalCount = ss.getTotalCount(staff);//结果总数
		List<Staff> stafflist = ss.getStaffPage(staff,indexPage,pageSize);
		PageHelperT<Staff> phs = new PageHelperT<Staff>(staff, stafflist, indexPage, pageSize, totalCount);
		//设置参数
		req.setAttribute("phs", phs);
		//转发到staff.jsp
		req.getRequestDispatcher("Staff.jsp").forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
