package com.chen.bdutilsTest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class druidtest {
	//申明连接池对象
	private static DruidDataSource ds;
	static {
		//实例化对象
		ds = new DruidDataSource();
		//实例化配置文件对象
		Properties properties = new Properties();
		try {
			InputStream fis = new FileInputStream("src/db.properties");
			properties.load(fis);
			ds.setDriverClassName(properties.getProperty("driverClassName"));
			ds.setUrl(properties.getProperty("url"));
			ds.setUsername(properties.getProperty("username"));
			ds.setPassword(properties.getProperty("password"));
			//设置最大连接数量
			ds.setMaxActive(Integer.parseInt(properties.getProperty("maxActive")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//返回datasource
	public static DataSource getdatasource() {
		return ds;
	}
	//获取链接对象
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
		}
		return null;
	}
	//查询
	public static void testquery() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("select * from book");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1)+"--"+rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		testquery();
	}
}
