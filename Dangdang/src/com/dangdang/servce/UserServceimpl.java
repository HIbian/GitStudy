package com.dangdang.servce;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dangdang.bean.User;
import com.dangdang.dao.UserDao;
import com.dangdang.dao.UserDaoimpl;

public class UserServceimpl implements UserServce{
	
	UserDao ud = new UserDaoimpl();
	
	@Override
	public boolean isRegisted(User u) {
		if (ud.getUserByUserName(u.getUsername())!=null) {//存在
			return true;
		}
		return false;//不存在
	}

	@Override
	public int userRegister(User u) {
		//判断是否重复了
		if (isRegisted(u)) {//用户名重复
			return -2;
		}
		if (ud.getUserByEmail(u.getEmail())!=null) {//邮箱重复
			return -3;
		}
		return ud.userRegister(u);
	}
	
	//判断用户名是否合法
	@Override
	public boolean isUserName(String username) {
		String pattern = "^(\\D)\\w{7,30}";
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(username);
		return matcher.matches();
	}

	@Override
	public boolean isPassword(String password) {
		String pattern = "\\w{6,30}";
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(password);
		return matcher.matches();
	}

	@Override
	public boolean isEmail(String email) {
		String pattern = "\\w+@\\w+.\\w+";
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(email);
		return matcher.matches();
	}
	//test
	public static void main(String[] args) {
		UserServceimpl us = new UserServceimpl();
		System.out.println(us.isUserName("a2243"));
	}

	@Override
	public User userLogin(String username, String password) {
		return ud.userLogin(username,password);
	}
}
