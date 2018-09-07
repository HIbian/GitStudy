package jdbc.study;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementStudy {
	public static void main(String[] args) {
		//获取数据库链接
		Connection conn = getConection();
		//创建Statement对象
		Statement statement=null;
		try {
			statement = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		boolean is=false;
		
		//execute
		 try {
			is = statement.execute("select * from student");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println(is);
		int count =0;
		try {
			count = statement.executeUpdate("insert into student values(200,'陈子昂',200,'男')");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
//		ResultSet executeQuery = statement.executeQuery("select * from student");
		
		System.out.println(count);
		
		//关闭数据库链接
		try {
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
