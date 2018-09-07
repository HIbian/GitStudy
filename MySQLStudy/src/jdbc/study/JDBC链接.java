package jdbc.study;

import java.sql.*;



public class JDBC链接 {
	public static void main(String[] args) throws ClassNotFoundException {
		
		//注册驱动方法一
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error:unable to find class");
			System.exit(1);
		}
		
		//注册驱动方法二
		try {
			Driver myDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(myDriver);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//数据库URL配置
		String url="jdbc:mysql://localhost:3306/testbd?useUnicode=true&characterEncoding=utf8";//地址加上指定数据库名
		String userName="root";
		String password="chenxin";
		Connection conn = null;
		try {
		 	conn = DriverManager.getConnection(url,userName,password);//返回一个connecting
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//关闭数据库
		try {
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
