package com.hr.servce;

import java.util.List;

import com.hr.bean.Dept;

public interface DeptServce {
	public List<Dept> getDepts();

	public Dept getDeptById(int dId);
}
