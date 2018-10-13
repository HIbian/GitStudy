package com.chen.bdutilsTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
	public static void main(String[] args) throws Exception{
		c3p0();
	}

	private static void c3p0() throws SQLException {
		// 1.创建C3P0连接池子
		Connection connection = c3p0test.getConnection();
		Statement createStatement = connection.createStatement();
		String sql = "select * from book;";
		ResultSet resultSet = createStatement.executeQuery(sql);
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1)+"--"+resultSet.getString(2));
		}
		c3p0test.close(connection, createStatement, resultSet);
	}
}
