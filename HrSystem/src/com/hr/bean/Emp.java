package com.hr.bean;

public class Emp {
	private int empId;
	//两个外键
	private int staffId;
	private int dId;
	//通过外键查询到的对象
	private Dept dept;
	private Staff staff;
	
	private String empName;
	private String gender;
	private String telNum;
	private String email;
	private String empEdu;
	private String cardNum;
	private String empAddress;
	private String empCreateTime;
	private String remark;
	private String habiit;
	private String political;
	private String qq;
	private String ems;
	private String birth;
	private String major;
	private String volk;
	private String phone;
	private String staffName;
	private String deptName;
	
	public Emp() {
		super();
	}
	public Emp(int staffId, int dId, String empName, String gender, String telNum, String cardNum) {
		super();
		this.staffId = staffId;
		this.dId = dId;
		this.empName = empName;
		this.gender = gender;
		this.telNum = telNum;
		this.cardNum = cardNum;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmpEdu() {
		return empEdu;
	}
	public void setEmpEdu(String empEdu) {
		this.empEdu = empEdu;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpCreateTime() {
		return empCreateTime;
	}
	public void setEmpCreateTime(String empCreateTime) {
		this.empCreateTime = empCreateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHabiit() {
		return habiit;
	}
	public void setHabiit(String habiit) {
		this.habiit = habiit;
	}
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEms() {
		return ems;
	}
	public void setEms(String ems) {
		this.ems = ems;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getVolk() {
		return volk;
	}
	public void setVolk(String volk) {
		this.volk = volk;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", staffId=" + staffId + ", dId=" + dId + ", dept=" + dept + ", staff=" + staff
				+ ", empName=" + empName + ", gender=" + gender + ", telNum=" + telNum + ", email=" + email
				+ ", empEdu=" + empEdu + ", cardNum=" + cardNum + ", empAddress=" + empAddress + ", empCreateTime="
				+ empCreateTime + ", remark=" + remark + ", habiit=" + habiit + ", political=" + political + ", qq="
				+ qq + ", ems=" + ems + ", birth=" + birth + ", major=" + major + ", volk=" + volk + ", phone=" + phone
				+ ", staffName=" + staffName + ", deptName=" + deptName + "]";
	}
}
