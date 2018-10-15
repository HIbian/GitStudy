package com.dangdang.bean;

public class User {
	private int id;
	private String email;
	private String username;
	private String password;
	private String phone;
	private String mobile;
	private String address;
	private String last_login_time;
	private String last_login_ip;
	private int deleteState;
	
	//登陆用
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	//注册用
	public User(String email, String username, String password, int deleteState) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.deleteState = deleteState;
	}
	//框架用
	public User() {}
	//查询用
	public User(int id, String email, String username, String password, String phone, String mobile, String address,
			String last_login_time, String last_login_ip, int deleteState) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.last_login_time = last_login_time;
		this.last_login_ip = last_login_ip;
		this.deleteState = deleteState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public int getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(int deleteState) {
		this.deleteState = deleteState;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + ", phone="
				+ phone + ", mobile=" + mobile + ", address=" + address + ", last_login_time=" + last_login_time
				+ ", last_login_ip=" + last_login_ip + ", deleteState=" + deleteState + "]";
	}
	
	
}
