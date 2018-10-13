package com.hr.servce;

import java.util.List;

import com.hr.bean.Emp;
import com.hr.dao.EmpDao;
import com.hr.dao.EmpDaoimpl;

public class EmpServceimpl implements EmpServce{
	EmpDao ed = new EmpDaoimpl();
	@Override
	public int getTotalCount(Emp emp) {
		return ed.getTotalCount(emp);
	}

	@Override
	public List<Emp> getEmpPage(Emp emp, int pageInex, int pageSize) {
		return ed.getEmpPage(emp, pageInex, pageSize);
	}
	@Override
	public int addEmp(Emp emp) {
		return ed.addEmp(emp);
	}

}
