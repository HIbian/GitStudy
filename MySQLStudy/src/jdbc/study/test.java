package jdbc.study;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
	public static void main(String[] args) {
		//获取数据库链接
		Connection conn = getConection();
		//创建Statement对象
		Statement statement=null;

		//executeUpdate
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery("select * from student");
			int clounm = resultSet.getMetaData().getColumnCount();
			for (int i = 1; i <= clounm; i++) {
				System.out.println(resultSet.getMetaData().getColumnName(i));
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			//关闭Statement对象
			statement.close();
			//关闭数据库链接
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//链接数据库
	public static Connection getConection() {
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
	}
}

