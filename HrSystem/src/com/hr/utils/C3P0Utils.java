package com.hr.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	//创建dataSource对象
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//返回dataSource对象
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
	//获取Connection对象
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//关闭链接
	public static void Close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void Close(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void Close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void Close(Connection conn,PreparedStatement ps) {
		Close(ps);
		Close(conn);
	}
	public static void Close(Connection conn,PreparedStatement ps,ResultSet rs) {
		Close(rs);
		Close(ps);
		Close(conn);
	}
}
