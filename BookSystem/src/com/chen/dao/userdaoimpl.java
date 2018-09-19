package com.chen.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.chen.bean.User;
import com.chen.utils.DaoUtil;

public class userdaoimpl implements userdao{
	
	//添加用户
	@Override
	public int register(User u) {
		return DaoUtil.Updata("insert into user values (null,?,?,?,?,?)", u.getUsername(),u.getPassword(),u.getSex(),u.getBirthday(),u.getEmail());
	}
	//用户登陆
	@Override
	public int login(User u) {
		ResultSet rs = DaoUtil.Query("select * from user where username=? and password=?", u.getUsername(),u.getPassword());
		try {
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				DaoUtil.CloseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
