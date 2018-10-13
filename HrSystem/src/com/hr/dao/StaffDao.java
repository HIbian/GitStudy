package com.hr.dao;

import java.util.List;
import com.hr.bean.Staff;

public interface StaffDao {
	public List<Staff> getStaff();
	public int getTotalCount(Staff staff);
	public List<Staff> getStaffPage(Staff staff, int indexPage, int pageSize);
	public Staff getStaffById(int staffId);
	public int updateStaff(Staff staff);
	public int deleteStaffById(int staffId);
}
