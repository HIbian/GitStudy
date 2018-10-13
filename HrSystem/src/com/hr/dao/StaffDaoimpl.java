package com.hr.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hr.bean.Staff;
import com.hr.utils.C3P0Utils;

public class StaffDaoimpl implements StaffDao{
	//获取数据源
	QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	@Override
	public List<Staff> getStaff() {
		try {
			//全查
			List<Staff> staffs = qr.query("select * from t_staff where delState !=1", new BeanListHandler<>(Staff.class));
			return staffs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getTotalCount(Staff staff) {
		int staffId = staff.getStaffId();
		String staffName = staff.getStaffName();
		StringBuffer sb = new StringBuffer("select count(*) from t_staff where delState=0");
		List<Object> parms = new ArrayList<>();
		if (staffId!=-1) {
			sb.append(" and staffId=?");
			parms.add(staffId);
		}
		if (staffName!="") {
			sb.append(" and staffName=?");
			parms.add(staffName);
		}
		try {
			Long totalCount;
			if (parms.size()!=0) {
				totalCount = qr.query(sb.toString(), new ScalarHandler<Long>(1), parms.toArray());
			}else {
				totalCount = qr.query(sb.toString(), new ScalarHandler<Long>(1));
			}
			return totalCount.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Staff> getStaffPage(Staff staff, int indexPage, int pageSize) {
		int staffId = staff.getStaffId();
		String staffName = staff.getStaffName();
		StringBuffer sb = new StringBuffer("select * from t_staff where delState=0");
		List<Object> parms = new ArrayList<>();
		if (staffId!=-1) {
			sb.append(" and staffId=?");
			parms.add(staffId);
		}
		if (staffName!="") {
			sb.append(" and staffName=?");
			parms.add(staffName);
		}
		//分页信息
		sb.append(" limit ?,?");
		parms.add((indexPage-1)*pageSize);
		parms.add(pageSize);
		
		try {
			List<Staff> stafflist = qr.query(sb.toString(), new BeanListHandler<>(Staff.class), parms.toArray());
			for (Staff s : stafflist) {
				System.out.println(s.toString());
			}
			return stafflist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Staff getStaffById(int staffId) {
		try {
			return qr.query("select * from t_staff where staffId=?", new BeanHandler<>(Staff.class), staffId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateStaff(Staff staff) {
		int staffId = staff.getStaffId();
		String staffName = staff.getStaffName();
		String staffDes = staff.getStaffDes();
		String sql = "update t_staff set staffName=?,staffDes=? where staffId=?";
		try {
			return qr.update(sql, staffName,staffDes,staffId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteStaffById(int staffId) {
		try {
			qr.update("delete from t_staff where staffId=?", staffId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
