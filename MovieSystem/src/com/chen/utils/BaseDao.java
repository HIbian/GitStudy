package com.chen.utils;
/*
 * 通用的数据库工具类
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	Connection conn=null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	//打开数据库
	public void openConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/MoveSystemBD", "root", "chenxin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//执行查询，返回resultSet
	public ResultSet Query(String sql,Object... parms) {
		//开启连接
		openConn();
		try {
			ps = conn.prepareStatement(sql);
			if (parms!=null) {
				for (int i = 0; i < parms.length; i++) {
					ps.setObject(i + 1, parms[i]);
				} 
			}
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//result还有用，暂时不用关
		return null;
	}
	//执行增删改
	public int Update(String sql,Object...parms) {
		openConn();
		try {
			ps = conn.prepareStatement(sql);
			if (parms!=null) {
				for (int i = 0; i < parms.length; i++) {
					ps.setObject(i+1, parms[i]);
				}
			}
			return ps.executeUpdate();
		} catch (Exception e) {
		}finally {
			closeConn();
		}
		return 0;
	}
	
	//关闭数据库
	public void closeConn() {
		try {
			if (rs!=null) {
				rs.close();
			}
			if (ps!=null) {
				ps.close();
			}
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}
	//test
//	public static void main(String[] args) {
//		BaseDao bd = new BaseDao();
//		System.out.println(bd.Update("insert into t_seat values (null,?,?)", 1,"1-1"));
//	}
}
