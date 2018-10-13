package com.hr.dao;

import java.util.List;

import com.hr.bean.Dept;

public interface DeptDao {
	public List<Dept> getDepts();

	public Dept getDeptById(int dId);
}
