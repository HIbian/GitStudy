package studentManageSystem.demo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToMySql {
	public static Connection contosql() throws SQLException {
		//注册驱动
		Driver myDriver = new com.mysql.jdbc.Driver();
		DriverManager.registerDriver(myDriver);
		
		//设置属性
		String url ="jdbc:mysql://localhost:3306/testbd?useUnicode=true&characterEncoding=utf8";
		String user = "root";
		String password ="chenxin";
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}
