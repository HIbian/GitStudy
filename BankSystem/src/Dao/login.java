package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Utils.BaseDao;

public class login {
	BaseDao bd = new BaseDao();
	Scanner sc = new Scanner(System.in);
	String username;
	String pass;
	//记录
	public void log() {
		System.out.println("输入用户名:");
		username = sc.next();		
		System.out.println("输入密码");
		pass = sc.next();
	}
	//登陆admin
	public boolean log_admin() {
		this.log();
		return bd.QueryisIn("select * from admin where username=? and password =?", username,pass);
	}
	//登陆用户
	public int log_user() {
		this.log();
		ResultSet rs = bd.Query("select id from customer where name=? and pass =?", username,pass);
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
