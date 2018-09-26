# 数据库连接池和DBUtils工具类

* 连接池原理：
  * 创建多个connection对象，放入连接池中，需要使用时直接拿出来用，省去了创建连接，连接数据库的时间，用完后放回连接池中。
* 开源数据库连接池
  * DBCP
  * C3P0
  * DRUID(德鲁伊)

## DBCP

* 全称database connection pool,数据库连接池.apache上的开源项目，也是tomcat使用的连接池组件，需要两个包

```java
package com.chen.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class BDCPtest {
	//创建数据源
	static BasicDataSource dataSource = new BasicDataSource();
	//设置连接信息，硬编码
	static{
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/testbd?useunicuee=true&characterEncoding=utf8");
		dataSource.setUsername("root");
		dataSource.setPassword("chenxin");
	}
	//获取一个connection对象
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//执行查询方法
	public static int Updata() {
		String sql = "select * from book";
		Connection conn =  getConnection();
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			 ps = conn.prepareStatement(sql);
			 rs  = ps.executeQuery();
			 while(rs.next()) {
				 for (int i = 1; i <=7; i++) {
					 System.out.print(rs.getString(i)+"\t");
				 }
				 System.out.println();
			 }
       //并不是关闭连接，而是回收对象
			 conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static void main(String[] args) {
		Updata();
	}
}

```
软编码,这是一个.properties文件
```go
#连接设置
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/day2
username=root
password=chenxin
#<!-- 初始化连接 -->
initialSize=10
#最大连接数量
maxActive=50
#<!-- 最大空闲连接 -->
maxIdle=20
#<!-- 最小空闲连接 -->
minIdle=5
#<!-- 超时等待时间以毫秒为单位 6000毫秒/1000等于60秒 -->
maxWait=6000
```
```java
@Test
	public void testSoft() throws Exception {
		BasicDataSourceFactory factory = new BasicDataSourceFactory();
		Properties properties = new Properties();
		// 配置文件添加到properties对象中 javase
		properties.load(new FileInputStream("src/info.properties"));
		// 生成连接池子 需要配置文件
		DataSource source = factory.createDataSource(properties);
		Connection connection = source.getConnection();
    ...
	}
```

## C3P0

* C3P0是一个开源的JDBC连接池，支持JDBC规范。目前使用它的开源项目有Hibernate，Spring等。
* C3P0与BDCP区别
  * 回收
    * BDCP没有自动回收空闲连接功能，手动close()
    * C3P0有自动回收空闲连接功能
  * 配置
    * BDCP手动写properties配置文件
    * C3P0自动读取xml配置文件
* C3P0的使用
  * 导SQL,C3P0包
  * 添加XML配置文件
    * c3p0是在外部添加配置文件,工具直接进行应用,因为直接引用,所以要求**固定的命名**和**文件位置**
    * 位置：scr
    * 名称：c3p0-config.xml
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <c3p0-config>
	     <!-- 默认配置，如果没有指定则使用这个配置 -->
       <default-config>
		      <!-- 基本配置 -->
    		<property name="driverClass">com.mysql.jdbc.Driver</property>
    		<property name="jdbcUrl">jdbc:mysql://localhost:3306/testbd?useunicuee=true&amp;characterEncoding=utf8</property>
    		<property name="user">root</property>
    		<property name="password">chenxin</property>
    		<!--扩展配置 -->
    		<!-- 连接超过30秒报错 -->
    		<property name="checkoutTimeout">30000</property>
    		<!--30秒检查空闲连接 -->
    		<property name="idleConnectionTestPeriod">30</property>
    		<property name="initialPoolSize">10</property>
    		<!-- 30秒不适用丢弃 -->
    		<property name="maxIdleTime">30</property>
    		<property name="maxPoolSize">100</property>
    		<property name="minPoolSize">10</property>
      </default-config>
    </c3p0-config>
    ```

**c3p0配置文件出错：实体之间要使用&隔开，而在xml文件中，用&amp;代替原来的&**

## DRUID
