package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BDconn {
	static String url = "jdbc:mysql://localhost:3306/usermanager?useunicuee=true&characterEncoding=utf8";
	static String username ="root";
	static String password = "chenxin";
	static Connection conn = null;
	static PreparedStatement  ps = null;
	//初始化数据
	public static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println("init [驱动加载失败]");
			e.printStackTrace();
		}
	}
	//关闭数据库
	public static void Close() {
		try {
			if (ps!=null) {
				ps.close();
			}
			if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//增删改
	public static int  addUpDelete(String sql,Object...parms) {
		try {
			ps = conn.prepareStatement(sql);
			for(int i=1;i<=parms.length;i++) {
				ps.setObject(i, parms[i-1]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Close();
		}
		return 0;
	}
	//执行查询语句,手动关闭
	public static ResultSet Query(String sql,Object...parms) {
		try {
			ps = conn.prepareStatement(sql);
			for(int i=1;i<=parms.length;i++) {
				ps.setObject(i, parms[i-1]);
			}
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
