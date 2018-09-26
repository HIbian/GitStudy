package com.chen.bdutilsTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p0test {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

	/**
	 * 返回连接池对象方法
	 * @return c3p0连接池子
	 */
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 连接池中获取连接的方法
	 * @return 连接
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	// 关闭资源
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement st) {

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet set) {
		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn, Statement st) {
		close(conn);
		close(st);
	}

	public static void close(Connection conn, Statement st, ResultSet rt) {
		close(conn);
		close(st);
		close(rt);
	}
}
