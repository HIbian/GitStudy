package com.chen.servce;

import com.chen.bean.User;
import com.chen.dao.userdao;
import com.chen.dao.userdaoimpl;

public class userservceimpl implements userservce{
	
	userdao ud = new userdaoimpl();
	//登陆
	@Override
	public int login(User u) {
		return ud.login(u);
	}
	//注册
	@Override
	public int register(User u) {
		return ud.register(u);
	}
	
}
