package com.hr.bean;

public class Staff {
	private int staffId;
	private String staffName;
	private String staffDes;
	private int delState;
	
	public Staff() {}
	
	public Staff(int staffId, String staffName) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
	}
	
	public Staff(String staffName, String staffDes) {
		super();
		this.staffName = staffName;
		this.staffDes = staffDes;
	}

	public Staff(int staffId, String staffName, String staffDes,int delState) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.delState = delState;
		this.staffDes = staffDes;
	}
	
	public Staff(int staffId, String staffName, String staffDes) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffDes = staffDes;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName + ", Des=" + staffDes + "]";
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffDes() {
		return staffDes;
	}
	public void setStaffDes(String staffDes) {
		this.staffDes = staffDes;
	}
	public int getDelState() {
		return delState;
	}
	public void setDelState(int delState) {
		this.delState = delState;
	}
	
	
}
