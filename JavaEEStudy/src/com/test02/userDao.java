package com.test02;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class userDao {
	//获取所有用户
	public ArrayList<Users> getUsers() {
		ArrayList<Users> us = new ArrayList<>();
		int id = 0;
		String username="";
		String userpass="";
		ResultSet rs = DaoUtil.Query("select * from t_users");
		try {
			while(rs.next()) {
				id = rs.getInt(1);
				username = rs.getString(2);
				userpass = rs.getString(3);
				us.add(new Users(id, username, userpass));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return us;
	}
	//删除用户
	public boolean delete(int id) {
		//验证用户是否存在
		ResultSet rs = DaoUtil.Query("select * from t_users where id=?", id);
		try {
			if (!rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (DaoUtil.Updata("delete from t_users where id=?",id)==1) {
			return true;
		}
		return false;
	}
	//修改密码
	public boolean xiugai(int id,String newpass) {
		//验证用户是否存在
		ResultSet rs = DaoUtil.Query("select * from t_users where id=?", id);
		try {
			if (!rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//若存在，修改密码
		if (DaoUtil.Updata("update t_users set userpass=? where id=?", newpass,id)==1) {
			return true;
		}
		return false;
	}
	//注册
	public boolean registered(String username,String userpass) {
		//验证用户名是否存在
		ResultSet rs = DaoUtil.Query("select * from t_users where username=?", username);
		try {
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//注册
		if (DaoUtil.Updata("insert into t_users values (null,?,?)", username,userpass)==1) {
			return true;
		}
		return false;
	}
	//查询是否存在
	public boolean isExist(String username,String userpass) {
		ResultSet rs = DaoUtil.Query("select * from t_users where username=? and userpass=?", username,userpass);
		try {
			if (rs.next()) {
				DaoUtil.CloseConn();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
