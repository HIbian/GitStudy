package com.hr.servce;

import java.util.List;

import com.hr.bean.Emp;

public interface EmpServce {
	//查询总数(带条件)
	public int getTotalCount(Emp emp);
	//查询一个list集合
	public List<Emp> getEmpPage(Emp emp,int pageInex,int pageSize);
	public int addEmp(Emp emp);
}
