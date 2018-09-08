package studentManageSystem.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManager implements IManager<Student>{

	public boolean add(Student t) {
		try {
			//判断是否合法

			//添加
			String sqlStr = "insert into manageStu values(?,?,?,?)";
			Connection conn = ConnectToMySql.contosql();
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, t.getSno());
			pstmt.setString(2,t.getSname());
			pstmt.setInt(3, t.getSage());
			pstmt.setString(4,String.valueOf(t.getSsex()));
			int count = pstmt.executeUpdate();
			//关闭对象
			pstmt.close();
			conn.close();
			if (count==1) {
				return true;
			}
		} catch (Exception e) {}
		return false;
	}

	public boolean delete(Student t) {
		try {
			Connection conn = ConnectToMySql.contosql();
			//删除
			String sqlStr = "delete from manageStu where sno=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, t.getSno());
			int count = pstmt.executeUpdate();
			//关闭对象
			pstmt.close();
			conn.close();
			if (count==1) {
				return true;
			}
		} catch (SQLException e) {}
		return false;
	}

	public boolean updata(Student new_t, Student old_t) {
		try {
			//修改
			String sqlStr = "update manageStu set sno=?,sname=?,sage=?,ssex=? where sno=?";
			Connection conn = ConnectToMySql.contosql();
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, new_t.getSno());
			pstmt.setString(2,new_t.getSname());
			pstmt.setInt(3, new_t.getSage());
			pstmt.setString(4,String.valueOf(new_t.getSsex()));
			pstmt.setInt(5, old_t.getSno());
			int count = pstmt.executeUpdate();
			//关闭对象
			pstmt.close();
			conn.close();
			if (count==1) {
				return true;
			}
		} catch (Exception e) {}
		return false;
	}

	public int query(Student t) {
		int count = 0;
		//查询
		try {
			Connection conn = ConnectToMySql.contosql();
			String sqlStr = "select * from manageStu where sno=? and sname=? and sage=? and ssex=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, t.getSno());
			pstmt.setString(2,t.getSname());
			pstmt.setInt(3, t.getSage());
			pstmt.setString(4,String.valueOf(t.getSsex()));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				count++;
			}
			//关闭对象
			pstmt.close();
			conn.close();
		} catch (SQLException e) {}
		return count;
	}

	public void print(){
		try {
			Connection conn = ConnectToMySql.contosql();
			PreparedStatement pstmt = conn.prepareStatement("select * from manageStu");
			ResultSet rs = pstmt.executeQuery();
			//获取列数
			int c = rs.getMetaData().getColumnCount();
			//打印表头
			for (int i = 1; i <= c; i++) {
				System.out.print(rs.getMetaData().getColumnName(i)+"\t");
			}
			System.out.println();
			//打印数据
			while (rs.next()) {
				for (int i = 1; i <= c; i++) {
					System.out.print(rs.getString(i)+"\t");
				}
				System.out.println();
			}
			//关闭对象
			rs.close();
			pstmt.close();
			conn.close();	
		} catch (SQLException e) {}
	}

}
