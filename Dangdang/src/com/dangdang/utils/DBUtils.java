package com.dangdang.utils;
/**
 * 
 * @author Administrator
 * 数据库工具类
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
	
	public static void name() {
		try {
			Connection conn = getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from d_product");
			ResultSet rs = ps.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <=columnCount; i++) {
					System.out.print(rs.getString(i)+"--");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		name();
	}
}
