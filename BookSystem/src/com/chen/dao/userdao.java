package com.chen.dao;

import com.chen.bean.User;

public interface userdao {
	//添加用户
	public int register(User u);
	//验证用户登陆
	public int login(User u);
}
