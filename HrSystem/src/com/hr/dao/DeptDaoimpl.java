package com.hr.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hr.bean.Dept;
import com.hr.utils.C3P0Utils;

public class DeptDaoimpl implements DeptDao{
	QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	@Override
	public List<Dept> getDepts() {
		try {
			List<Dept> depts = qr.query("select * from t_dept where delState=0", new BeanListHandler<>(Dept.class));
			return depts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Dept getDeptById(int dId) {
		try {
			return qr.query("select * from t_dept where did=?", new BeanHandler<>(Dept.class), dId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
