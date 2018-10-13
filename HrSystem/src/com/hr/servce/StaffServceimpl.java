package com.hr.servce;

import java.util.List;

import com.hr.bean.Staff;
import com.hr.dao.StaffDao;
import com.hr.dao.StaffDaoimpl;

public class StaffServceimpl implements StaffServce{
	
	StaffDao sd = new StaffDaoimpl();
	@Override
	public List<Staff> getStaff() {
		return sd.getStaff();
	}
	@Override
	public int getTotalCount(Staff staff) {
		return sd.getTotalCount(staff);
	}
	@Override
	public List<Staff> getStaffPage(Staff staff, int indexPage, int pageSize) {
		return sd.getStaffPage(staff,indexPage,pageSize);
	}
	@Override
	public Staff getStaffById(int staffId) {
		return sd.getStaffById(staffId);
	}
	@Override
	public int updateStaff(Staff staff) {
		return sd.updateStaff(staff);
	}
	@Override
	public int deleteStaffById(int staffId) {
		return sd.deleteStaffById(staffId);
	}

}
