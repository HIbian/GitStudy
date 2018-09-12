[TOC]
# JDBC高级操作
## PrepareStatement批处理
mysql中**自动提交**是**默认开启**的
要进行批处理的操作，首先需要关闭自动提交

mysql
```sql
--0表示关闭
SET AUTOCOMMIT=0;
--1表示开启
SET AUTOCOMMIT=1;
```
java
```java
Connection.setAutoCommit(false);
```
```java
conn.setAutoCommit(false);
String sql = "insert into t_name values (?,?)";
PrepareStatement ps = conn.PrepareStatement(sql);
ps.setObject(1,x);
ps.setObject(2,x);
ps.addBatch();//添加到批处理中
ps.setObject(1,x);
ps.setObject(2,x);
ps.addBatch();
//添加完成后一起执行
ps.executeUpdate();//返回一个int数组表示每一次Batch影响的行数
//关闭自动提交后需要手动提交，不然mysql会自动回滚
conn.Commit();
```
## 使用JDBC处理二进制数据
mysql的Blob类型，可以通过流的方式存储一个文件，这里用图片作为例子
```java
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
```
## JDBC事务
**事务指逻辑上的一组操作，组成这组操作的各个单元，要不全部成功，要不全部不成功。**
mysql中开启事务，开启后需要手动提交，sql语句才会生效
提交之前mysql出现错误情况会自动回滚（Rollback）至开启事务前的状态或者回滚至回滚点（Savepoint）。
* 开启事务
```java
conn.setAutoCommit(false);
```
* 回滚事务(回滚未提交的事务，回滚至开始事务时)
```java
conn.Rollback();
```
* 提交事务(提交后永久改变数据库数据)
```java
conn.Commit();
```

* 设置回滚点(可自定义那些数据需要回滚，那些不需要回滚)
```java
Savepoint sp = conn.setSavepoint();
conn.Rollback(sp);//回滚至指定回滚点
//放入catch中，出现异常时可以回滚至指定位置
try{
  ...//回滚点之前的都会被commit
  Savepoint sp = conn.setSavepoint();
  ...//出现异常的代码
}catch (Exception e) {
  conn.Rollback(sp);
  conn.Commit();
}
```
## 事务的四大特性
* 原子性：要么全部成功，要么全部失败
* 一致性：事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
* 隔离性：数据库为每一个用户开启的事务，不能被其他事务的操作数据所干扰，多个并发事务之间要相互隔离。
* 持久性：事务一旦被提交，它对数据库中数据的改变就是永久性的。

## 事务的隔离级别
* Serializable(串行化)：可避免脏读、不可重复读、虚读情况的发生。
* Repeatable read(可重复读)：可避免脏读、不可重复读情况的发生。
* Read committed(读已提交,不可重复读：一个事务范围内两个相同的查询却返回了不同数据)：可避免脏读情况发生。
* Read uncommitted(读未提交,可脏读)：最低级别，以上情况均无法保证。
