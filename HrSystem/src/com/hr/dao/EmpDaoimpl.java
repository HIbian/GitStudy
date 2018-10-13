package com.hr.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hr.bean.Dept;
import com.hr.bean.Emp;
import com.hr.bean.Staff;
import com.hr.utils.C3P0Utils;

public class EmpDaoimpl implements EmpDao{
	QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	@Override
	public int getTotalCount(Emp emp) {
		int staffId = emp.getStaffId();//-1 全查
		String empName = emp.getEmpName();
		String cardNum = emp.getCardNum();
		String gender = emp.getGender();//-1 全查
		int did = emp.getdId();//-1 全查
		String telNum = emp.getTelNum();
		
//		System.out.println(staffId+"--"+empName+"--"+cardNum+"--"+gender+"--"+telNum+"--"+did);

		StringBuffer sb = new StringBuffer("select count(*) from t_emp where 1=1");
		ArrayList<Object> parms = new ArrayList<>();
		if (staffId!=-1) {
			sb.append(" and staffId=?");
			parms.add(staffId);
		}
		if (empName!=null) {
			sb.append(" and empName like ?");
			parms.add("%"+empName+"%");
		}
		if (cardNum!=null) {
			sb.append(" and cardNum=?");
			parms.add(cardNum);
		}
		if (!gender.equals("-1")) {
			sb.append(" and gender=?");
//			System.out.println(gender.equals("0")?"男":"女");
			parms.add(gender.equals("0")?"男":"女");
		}
		if (did!=-1) {
			sb.append(" and did=?");
			parms.add(did);
		}
		if (telNum!=null) {
			sb.append(" and telNum=?");
			parms.add(telNum);
		}
		System.out.println(sb.toString());
		try {
			Long query=null;
			if (parms.size()!=0) {
				query = qr.query(sb.toString(), new ScalarHandler<Long>(1), parms.toArray());
			}else {
				query = qr.query(sb.toString(), new ScalarHandler<Long>(1));
			}
			return query.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Emp> getEmpPage(Emp emp, int pageInex, int pageSize) {
		int staffId = emp.getStaffId();//-1 全查
		String empName = emp.getEmpName();
		String cardNum = emp.getCardNum();
		String gender = emp.getGender();//-1 全查
		int did = emp.getdId();//-1 全查
		String telNum = emp.getTelNum();

		StringBuffer sb = new StringBuffer("select * from t_emp where 1=1");
		ArrayList<Object> parms = new ArrayList<>();
		if (staffId!=-1) {
			sb.append(" and staffId=?");
			parms.add(staffId);
		}
		if (empName!=null) {
			sb.append(" and empName like ?");
			parms.add("%"+empName+"%");
		}
		if (cardNum!=null) {
			sb.append(" and cardNum=?");
			parms.add(cardNum);
		}
		if (!gender.equals("-1")) {
			sb.append(" and gender=?");
//			System.out.println(gender.equals("0")?"男":"女");
			parms.add(gender.equals("0")?"男":"女");
		}
		if (did!=-1) {
			sb.append(" and did=?");
			parms.add(did);
		}
		if (telNum!=null) {
			sb.append(" and telNum=?");
			parms.add(telNum);
		}
		
		//添加分页信息
		sb.append(" limit ?,?");
		parms.add((pageInex-1)*pageSize);
		parms.add(pageSize);
		
		System.out.println(sb.toString());
		try {
			//执行查询
			List<Emp> emplist = qr.query(sb.toString(), new BeanListHandler<>(Emp.class), parms.toArray());
			//添加部门对象，职位对象
			for (Emp e : emplist) {
				int stffId = e.getStaffId();
				Staff staff = qr.query("select * from t_staff where staffId=? and delState=0", new BeanHandler<>(Staff.class), stffId);
				if (staff!=null) {
					e.setStaff(staff);
				}
				
				int deptId = e.getdId();
				Dept dept = qr.query("select * from t_dept where did=? and delState=0", new BeanHandler<>(Dept.class), deptId);
				if (dept!=null) {
					e.setDept(dept);
				}
			}
			
			return emplist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 添加emp
	 */
	@Override
	public int addEmp(Emp emp) {
		List<Object> parms = new ArrayList<>();
		parms.add(emp.getEmpName());
		parms.add(emp.getGender()=="0"?"男":"女");
		parms.add(emp.getTelNum());
		parms.add(emp.getEmail());
		parms.add(emp.getStaffId());
		parms.add(emp.getEmpEdu());
		parms.add(emp.getCardNum());
		parms.add(emp.getdId());
		parms.add(emp.getEmpAddress());
		parms.add(emp.getEmpCreateTime());
		parms.add(emp.getRemark());
		parms.add(emp.getHabiit());
		parms.add(emp.getPolitical());
		parms.add(emp.getQq());
		parms.add(emp.getEms());
		parms.add(emp.getBirth());
		parms.add(emp.getMajor());
		parms.add(emp.getVolk());
		parms.add(emp.getPhone());
		parms.add(emp.getStaffName());
		parms.add(emp.getDeptName());
		String sql = "insert into t_emp values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			return qr.update(sql, parms.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
