

上机练习：

1. 准备一张数据表，字段自拟 例如 动物数据表、学生数据表 ，还有一张用户表(登陆和注册)
2. 使用用户表完成登陆和注册的功能
3. 使用普通数据表 完成
4. 根据条件查询
5. 根据条件更新
6. 根据条件删除 新增


```java
package jdbc.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC作业 {

	public static void main(String[] args) {
//		homework1();
//		homework2_idQuery();
//		homework2_Update();
//		homeword2_Add();
		homework2_Delete();
	}
	public static void homework2_Delete() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("输入id号：");
		int id = sc.nextInt();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/test_in_ubuntu?useUnicode=true&characterEncoding=utf8", "root", "chenxin");
			String sql = "delete from t_stu where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int count= ps.executeUpdate();
			if (count==1) {
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally {
			try {
				sc.close();
				if (rs!=null) {
					rs.close();
				}
				if (ps!=null) {
					ps.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void homeword2_Add() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("输入姓名");
		String name = sc.next();
		System.out.println("输入年龄");
		int age = sc.nextInt();
		System.out.println("输入性别");
		String sex =  sc.next();
		System.out.println("输入身高");
		double height = sc.nextDouble();
		System.out.println("输入体重");
		double weight = sc.nextDouble();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/test_in_ubuntu?useUnicode=true&characterEncoding=utf8", "root", "chenxin");
			String sql = "insert into t_stu values (null,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, name);
			ps.setObject(2, age);
			ps.setObject(3, sex);
			ps.setObject(4, height);
			ps.setObject(5, weight);
			int count= ps.executeUpdate();

			if (count==1) {
				System.out.println("添加成功");
			}else {
				System.out.println("添加失败");
			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally {
			try {
				sc.close();
				if (rs!=null) {
					rs.close();
				}
				if (ps!=null) {
					ps.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void homework2_Update() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("输入id号：");
		int id = sc.nextInt();
		System.out.println("输入姓名");
		String name = sc.next();
		System.out.println("输入年龄");
		int age = sc.nextInt();
		System.out.println("输入性别");
		String sex =  sc.next();
		System.out.println("输入身高");
		double height = sc.nextDouble();
		System.out.println("输入体重");
		double weight = sc.nextDouble();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/test_in_ubuntu?useUnicode=true&characterEncoding=utf8", "root", "chenxin");
			String sql = "update t_stu set sname=?,sage=?,ssex=?,shight=?,sweight=? where id =?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, name);
			ps.setObject(2, age);
			ps.setObject(3, sex);
			ps.setObject(4, height);
			ps.setObject(5, weight);
			ps.setInt(6, id);
			int count= ps.executeUpdate();

			if (count==1) {
				System.out.println("跟新成功");
			}else {
				System.out.println("更新失败");
			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally {
			try {
				sc.close();
				if (rs!=null) {
					rs.close();
				}
				if (ps!=null) {
					ps.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void homework2_idQuery() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("输入id号：");
		int id = sc.nextInt();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/test_in_ubuntu?useUnicode=true&characterEncoding=utf8", "root", "chenxin");
			String sql = "select * from t_stu where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			int count=0;
			while (rs.next()) {
				count++;
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t");
			}
			if (count==0) {
				System.out.println("没有查询结果");
			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally {
			try {
				sc.close();
				if (rs!=null) {
					rs.close();
				}
				if (ps!=null) {
					ps.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void homework1() {
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		Scanner sc = new Scanner(System.in);
		System.out.println("输入用户名：");
		String username = sc.next();
		System.out.println("输入密码：");
		String password = sc.next();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://120.79.74.232:3306/test_in_ubuntu?useUnicode=true&characterEncoding=utf8", "root", "chenxin");
			String sql="select * from t_user where username=? and password =?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, username);
			ps.setObject(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("欢迎"+username);
			}else {
				System.out.println("账号或密码错误！！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				sc.close();
				if (rs!=null) {
					rs.close();
				}
				if (ps!=null) {
					ps.close();
				}
				if (conn!=null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

```
