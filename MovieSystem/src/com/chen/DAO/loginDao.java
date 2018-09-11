package com.chen.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.chen.utils.*;

public class loginDao {

	String username = "";
	String password = "";
	public loginDao() {
	}
	public loginDao(String username,String password) {
		this.username = username;
		this.password = password;
	}
	//验证账号密码是否正确
	public boolean login() {
		BaseDao bd = new BaseDao();
		ResultSet rs = bd.Query("select * from t_user where username=? and password=?", username,password);
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			bd.closeConn();
		}
		return false;
	}
	//获取账号权限
	public int getRight() {
		BaseDao bd = new BaseDao();
		ResultSet rs = bd.Query("select * from t_user where username=? and password=?", username,password);
		int right =-1;
		try {
			rs.next();
			right = rs.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			bd.closeConn();
		}
		return right;
	}
	//
	//test
//	public static void main(String[] args) {
//		loginDao lDao = new loginDao("admin", "123456");
//		System.out.println(lDao.getRight());
//	}
}
