package com.hr.servce;

import java.util.List;

import com.hr.bean.Dept;
import com.hr.dao.DeptDao;
import com.hr.dao.DeptDaoimpl;

public class DeptServceimpl implements DeptServce{
	DeptDao dd = new DeptDaoimpl();
	@Override
	public List<Dept> getDepts() {
		return dd.getDepts();
	}
	@Override
	public Dept getDeptById(int dId) {
		return dd.getDeptById(dId);
	}

}
