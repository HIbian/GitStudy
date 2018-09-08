package jdbc.study;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatmentStudy {
	public static void main(String[] args) {
		Connection conn = getConection();
		PreparedStatement pstmt = null;
		int count =0;
		try {
			//注意使用英文逗号，不然会提示Parameter index out of range (1 > number of parameters, which is 0).
			String sql = "Insert into student values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			//设置问号的值，下表从1开始
			pstmt.setInt(1, 255);
			pstmt.setString(2, "陈子");
			pstmt.setInt(3, 26);
			pstmt.setString(4, "男");
			//执行语句，返回影响行数
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("影响的行数："+count);
		//关闭
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	//链接数据库
	public static Connection getConection() {
		//注册驱动1
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//注册驱动2
		try {
			Driver myDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(myDriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//建立链接
		String url ="jdbc:mysql://localhost:3306/testbd?useUnicode=true&characterEncoding=utf8";
		String user ="root";
		String password ="chenxin";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
//		//关闭
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
}
