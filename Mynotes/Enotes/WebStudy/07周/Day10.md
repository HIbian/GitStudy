[TOC]
## jdbc笔记

# 连接数据库

* **jdbc核心组件**
  * DriverManager
  * Driver 一般使用DriverManager来管理
  * Connection 表示物理连接
  * Statement 执行查询过程
  * ResultSet 保存返回的数据


* 与MYSQL建立链接
  * 添加jdbc包
  * 导入java.sql*;
  * 注册驱动
  * 配置链接属性，使用getConnection链接
  * 使用.close()关闭
  * ps:配置url时在末尾添加***?useUnicode=true&characterEncoding=utf8***使包中字符与mysql字符集相同


* 需要填充的几个属性
  * 注册驱动时com.mysql.jdbc.Drive或者com.mysql.jdbc.Driver()
  * 链接数据库时url="jdbc:mysql://localhost:3306/testbd?useUnicode=true&characterEncoding=utf8"，user，password
    * url中填入jdbc：mysql：//hostname（服务器地址） / databaseName（数据库名）
```java
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
		String password="这是密码";
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
```

# 使用Statement和PreparedStatement执行sql语句
链接数据库成功后，就需要进行查询，添加，删除，修改的操作了.
我们需要通过Connection对象的**CreateStatement()**方法获取一个Statement对象.

*什么是Statement?*
*一旦获得了连接，我们可以与数据库进行交互。Statement用于对数据库进行通用访问。在运行时使用静态SQL语句时很有用。Statement接口不能接受参数。*

Statement下有三个常用方法
* boolean execute（String SQL）：如果可以检索到ResultSet对象，则返回一个布尔值true; 否则返回false。使用此方法执行SQL DDL语句或需要使用真正的动态SQL时。
* int executeUpdate（String SQL）：返回受SQL语句执行影响的行数。使用此方法执行预期会影响多个行的SQL语句，例如INSERT，UPDATE或DELETE语句。
* ResultSet executeQuery（String SQL）：返回一个ResultSet对象。当您希望获得结果集时，请使用此方法，就像使用SELECT语句一样。

来操作一下
这里我使用getConnection()封装了链接数据库的过程，返回一个Connection对象用于创建我们的Statement，也方便之后关闭数据库链接

```java
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
		System.out.println("返回结果是否有ResultSet:"+is);
		//executeUpdate
		int count =0;
		try {
			count = statement.executeUpdate("insert into student values(13,'子昂',17,'男')");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("返回影响的行数:"+count);
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
		String password ="这是密码";
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

```
除了Statement外，还可以使用PreparedStatement。
使用PreparedStatement可以方便地传入可变的数据
* 使用步骤如下
  * 创建一个sql语句，用英文的?表示变量
  * 使用conn.prepareStatement(sql);返回一个PreparedStatement对象
  * 使用setXX(index,values);来设置每个?的值，下表从1开始,XX为数据在Java中的类型
  * 同样，使用close()关闭该对象

```java
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
		String password ="这是密码";
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
```
# 遍历查询结果resultset
前面的语句都没有获取一个具体的表单，通过ResultSet可以获取一个数据集合
遍历一个表单步骤如下
* 通过executeQuery()获取Resultset
* ResultSet.getMetaData().getColumnCount;获取表列数
* 循环打印列名 ResultSet.getMetaData().getColumnName(index)，index从一开始
  * ResultSet.next()指到下一行（第一行）.循环
    * ResultSet.getString(columnIndex);该行第columnindex列的数据.循环
* 最后记得关闭resultset，prepareStatement,Connection

```java
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
		String password ="这是密码";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

```
