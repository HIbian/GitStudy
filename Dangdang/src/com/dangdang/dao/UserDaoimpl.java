package com.dangdang.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dangdang.bean.User;
import com.dangdang.utils.DBUtils;

public class UserDaoimpl implements UserDao{
	QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
	
	//用户注册
	@Override
	public int userRegister(User u) {
		String username = u.getUsername();
		String password = u.getPassword();
		String email = u.getEmail();
		try {
			return qr.update("insert into d_user values(null,?,?,?,null,null,null,null,null,0)", email,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public User getUserByUserName(String username) {
		try {
			return qr.query("select * from d_user where username=?", new BeanHandler<>(User.class), username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public User getUserByEmail(String email) {
		try {
			return qr.query("select * from d_user where email=?", new BeanHandler<>(User.class), email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User userLogin(String username, String password) {
		try {
			return  qr.query("select * from d_user where deleteState=0 and username=? and password=?", new BeanHandler<>(User.class),username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
