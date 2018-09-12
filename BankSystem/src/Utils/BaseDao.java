package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	Connection conn=null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	//连接数据库
	public void openConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/Bank", "root", "chenxin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//执行查询，返回bool
	public boolean QueryisIn(String sql,Object... parms) {
		//开启连接
		openConn();
		try {
			ps = conn.prepareStatement(sql);
			if (parms!=null) {
				for (int i = 0; i < parms.length; i++) {
					ps.setObject(i + 1, parms[i]);
				} 
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
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
	//转账事物
	public boolean giveMoney(int id,String num,double money) {
		openConn();
		try {
			conn.setAutoCommit(false);
			//转出方
			String sql1 = "update customer set money=money-? where id=?";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setDouble(1, money);
			ps1.setInt(2, id);
			ps1.executeUpdate();
			//转入方
			String sql2 = "update customer set money=money+? where number=?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setDouble(1, money);
			ps2.setString(2, num);
			ps2.executeUpdate();
			//提交
			conn.commit();
			conn.setAutoCommit(true);
			//关流
			ps1.close();
			ps1.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			closeConn();
		}
	}
}
