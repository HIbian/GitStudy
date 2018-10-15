package com.dangdang.dao;

import com.dangdang.bean.User;

public interface UserDao {
	public int userRegister(User u);
	public User getUserByUserName(String username);
	public User getUserByEmail(String email);
	public User userLogin(String username, String password);
}
