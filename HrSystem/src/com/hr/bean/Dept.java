package com.hr.bean;

public class Dept {
	private int did;
	private String deptName;
	private String deptDes;
	private int delState;
	
	public Dept() {
	}
	public Dept(int did, String deptName, String deptDes,int delState) {
		super();
		this.did = did;
		this.deptName = deptName;
		this.deptDes = deptDes;
		this.delState= delState;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptDes() {
		return deptDes;
	}
	public void setDeptDes(String deptDes) {
		this.deptDes = deptDes;
	}
	public int getDelState() {
		return delState;
	}
	public void setDelState(int delState) {
		this.delState = delState;
	}
	@Override
	public String toString() {
		return "Dept [did=" + did + ", deptName=" + deptName + ", deptDes=" + deptDes + ", delState=" + delState + "]";
	}
}
