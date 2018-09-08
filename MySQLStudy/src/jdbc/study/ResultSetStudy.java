package jdbc.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetStudy {
	public static void main(String[] args) {
		try{
			Connection conn = getConection();
			String sql = "select * from student where ssex=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "男");
			ResultSet resultSet = pstmt.executeQuery();
			//获取表列数
			int colunm = resultSet.getMetaData().getColumnCount();
			for (int i = 1; i <= colunm; i++) {
				//打印列名
				System.out.print(resultSet.getMetaData().getColumnName(i)+"\t");
			}
			System.out.println();
			int countRows = 0;
			//遍历表数据
			while (resultSet.next()) {
				for (int i = 1; i <= colunm; i++) {
					System.out.print(resultSet.getString(i)+"\t");
				}
				System.out.println();
				countRows++;
			}
			System.out.println("一共有"+countRows+"行");
			
			resultSet.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//链接数据库
	public static Connection getConection() {
		//注册驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
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
