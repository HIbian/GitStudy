package com.test02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtil {
	static Connection conn = null;
	static PreparedStatement ps = null;
	//创建连接
	public static void ConnToMysql() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbd?useunicuee=true&characterEncoding=utf8","root","chenxin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//执行增删改查
	public static int Updata(String sql,Object...params) {
		ConnToMysql();
		try {
			ps = conn.prepareStatement(sql);
			if (params!=null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1,params[i]);
				}
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseConn();
		}
		return 0;
	}
	//执行查询语句
	public static ResultSet Query(String sql,Object...params) {
		ConnToMysql();
		try {
			ps = conn.prepareStatement(sql);
			if (params!=null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1,params[i]);
				}
			}
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//关闭连接
	public static void CloseConn() {
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
}
