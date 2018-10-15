package com.dangdang.servce;

import com.dangdang.bean.User;

public interface UserServce {
	//查询用户是否重复
	public boolean isRegisted(User u);
	//注册用户
	public int userRegister(User u);
	//判断用户名是否合法
	public boolean isUserName(String username);
	//判断密码是否合法
	public boolean isPassword(String password);
	//判断邮箱是否合法
	public boolean isEmail(String email);
	public User userLogin(String username, String password);
}
