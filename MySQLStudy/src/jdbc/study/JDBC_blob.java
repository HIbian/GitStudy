package jdbc.study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_blob {
	public static void main(String[] args) {
		//存图片
		Connection conn= null;
		PreparedStatement ps= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/test_in_ubuntu", "root", "chenxin");
			ps = conn.prepareStatement("insert into t_blob values (null,?)");
			File file = new File("F:\\test\\2.jpg");
			InputStream is = new FileInputStream(file);
			ps.setBlob(1, is	, file.length());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//取图片
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/test_in_ubuntu", "root", "chenxin");
			ps = conn.prepareStatement("select * from t_blob where id =1");
			File file = new File("F:\\test\\Letter\\2.jpg");
			ResultSet rs = ps.executeQuery();
			rs.next();
			Blob blob = rs.getBlob(2);
			InputStream is = blob.getBinaryStream();
			FileOutputStream os = new FileOutputStream(file);
			byte[] bs = new byte[10];
			int len = 0;
			while((len = is.read(bs))>0) {
				os.write(bs,0,len);
			}
			os.close();
			is.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
