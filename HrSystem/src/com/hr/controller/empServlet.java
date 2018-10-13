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

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.hr.bean.Dept;
import com.hr.bean.Emp;
import com.hr.bean.Staff;
import com.hr.servce.DeptServce;
import com.hr.servce.DeptServceimpl;
import com.hr.servce.EmpServce;
import com.hr.servce.EmpServceimpl;
import com.hr.servce.StaffServce;
import com.hr.servce.StaffServceimpl;
import com.hr.utils.PageHelperT;

@WebServlet("/empServlet.do")
public class empServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StaffServce ss = new StaffServceimpl();
	DeptServce ds = new DeptServceimpl();
	EmpServce es = new EmpServceimpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取行动参数
		String action = request.getParameter("action");
		if ("query".equals(action)) {//查询员工表，多表查询
			query(request, response);
		}else if ("getStaffs".equals(action)) {//获取所有职位列表
			getStaffs(response);
		}else if ("getDepts".equals(action)) {//获取所有部门列表
			getDepts(response);
		}else if ("addEmp".equals(action)) {//添加Emp员工
			Emp emp = new Emp();
			try {
				BeanUtils.populate(emp, request.getParameterMap());
				//添加创建时间
				emp.setEmpCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				//添加部门名和职位名
				emp.setDeptName(ds.getDeptById(emp.getdId()).getDeptName());
				emp.setStaffName(ss.getStaffById(emp.getStaffId()).getStaffName());
//				System.out.println(emp.toString());
				//添加到数据库中
				int count = es.addEmp(emp);
				if (count>0) {
					System.out.println("添加成功");
					response.sendRedirect("empServlet.do?action=query");
				}else {
					System.out.println("添加失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void getDepts(HttpServletResponse response) throws IOException {
		List<Dept> depts = ds.getDepts();
		String jsonString = JSON.toJSONString(depts);//出错，没有dName
//			System.out.println(jsonString);
		response.getWriter().write(jsonString);
	}

	private void getStaffs(HttpServletResponse response) throws IOException {
		List<Staff> staff = ss.getStaff();
		String jsonString = JSON.toJSONString(staff);
//			System.out.println(jsonString);
		response.getWriter().write(jsonString);
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String staffId = request.getParameter("staffName")==null?"-1":request.getParameter("staffName");
		String empName = request.getParameter("empName")==""?null:request.getParameter("empName");
		String cardNum = request.getParameter("cardNum")==""?null:request.getParameter("cardNum");
		String gender = request.getParameter("gender")==null?"-1":request.getParameter("gender");
		String telNum = request.getParameter("telNum")==""?null:request.getParameter("telNum");
		String dId = request.getParameter("deptName")==null?"-1":request.getParameter("deptName");
		
		int pageIndex = request.getParameter("pageIndex")==null?1:Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = request.getParameter("pageSize")==null?5:Integer.parseInt(request.getParameter("pageSize"));
		
		System.out.println(staffId+"--"+empName+"--"+cardNum+"--"+gender+"--"+telNum+"--"+dId);
		Emp emp = new Emp(Integer.parseInt(staffId), Integer.parseInt(dId), empName, gender, telNum, cardNum);
		//获取数据个数
		int totalCount = es.getTotalCount(emp);
		if (totalCount==0) {//没有查询到结果
			
		}
		List<Emp> empPage = es.getEmpPage(emp, pageIndex, pageSize);
		System.out.println("------------------------------------");
		for (Emp e : empPage) {
			System.out.println(e.toString());
		}
		System.out.println("------------------------------------");
		//数据回显，需要PageHelperT传递数据到jsp上
		PageHelperT<Emp> pageHelper = new PageHelperT<Emp>(emp, empPage, pageIndex, pageSize, totalCount);
		//+++获取页码按钮信息
		int[] pageIndexList = getPageIndexList(pageIndex, pageSize, totalCount);
		//设置属性到requset
		request.setAttribute("pageList", pageIndexList);
		request.setAttribute("pg", pageHelper);
		request.getRequestDispatcher("emp.jsp").forward(request, response);
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
