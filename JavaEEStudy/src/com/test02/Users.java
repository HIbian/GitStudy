package com.test02;

public class Users {
	int id;
	String username;
	String userpass;
	public Users() {
	}
	public Users(int id, String username, String userpass) {
		super();
		this.id = id;
		this.username = username;
		this.userpass = userpass;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", userpass=" + userpass + "]";
	}
}
