package com.entity;
 /*
  * 用户实体类
  */
public class User {
    private int id;
    private String name;
    private String pwd;
    private String sex;
    private String home;
    private String info;
    public User() {
	}
	public User(int id,String name, String pwd, String sex, String home, String info) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.home = home;
		this.info = info;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
    
}
