# Hibernate 基础

创建session的过程
```java
public class test {
    @Test
    public void test(){
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.close();
    }
}
```

## POM 导入hibernate所需包

```xml
<dependencies>
		<!-- Hiberante核心包 -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.3.3.Final</version>
		</dependency>
		<!-- mysql驱动包 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.12</version>
		</dependency>
	</dependencies>
```

## 配置Hibernate的配置文件：hibernate.cfg.xml
**配置文件名字不能改！配置的是：Hibernate连接数据库的信息！后面还会配置mapping映射**

```xml
<!DOCTYPE hibernate-configuration PUBLIC
	"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
	"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
	<session-factory>
    <!--方言 -->
		<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
    <!--驱动 -->
		<property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <!--url -->
		<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/dangdang?useSSL=false&amp;serverTimezone=UTC&amp;charset=UTF-8</property>
    <!-- 账号密码 -->
		<property name="hibernate.connection.username">root</property>
		<property name="hibernate.connection.password">chenxin</property>

    <!-- 打印sql语句 -->
		<property name="show_sql">true</property>
		<property name="format_sql">true</property>

		<!-- <mapping resource="/..."></mapping> -->

	</session-factory>
</hibernate-configuration>
```

## 配置实体类与mysql数据表的映射关系xml
```java
package aaaaa.bean;

import java.util.Date;

public class Product {
	private Integer id;
	private String product_name;
	private String description;
	private Date add_time;
	private String count;
	private Double fixed_price;
	private Double dd_price;
	private Integer keyset;
	private String product_pciture;
	private Integer deleteState;
	public Product(String product_name) {
		super();
		this.product_name = product_name;
	}
	public Product(Integer id, String product_name, String description, Date add_time, String count, Double fixed_price,
			Double dd_price, Integer keyset, String product_pciture, Integer deleteState) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.description = description;
		this.add_time = add_time;
		this.count = count;
		this.fixed_price = fixed_price;
		this.dd_price = dd_price;
		this.keyset = keyset;
		this.product_pciture = product_pciture;
		this.deleteState = deleteState;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getDescription() {
		return description;

	public void setDescription(String description) {
		this.description = description;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Double getFixed_price() {
		return fixed_price;
	}
	public void setFixed_price(Double fixed_price) {
		this.fixed_price = fixed_price;
	}
	public Double getDd_price() {
		return dd_price;
	}
	public void setDd_price(Double dd_price) {
		this.dd_price = dd_price;
	}
	public Integer getKeyset() {
		return keyset;
	}
	public void setKeyset(Integer keyset) {
		this.keyset = keyset;
	}
	public String getProduct_pciture() {
		return product_pciture;
	}
	public void setProduct_pciture(String product_pciture) {
		this.product_pciture = product_pciture;
	}
	public Integer getDeleteState() {
		return deleteState;
	}
	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
	}
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", product_name=" + product_name + ", description=" + description + ", add_time="
				+ add_time + ", count=" + count + ", fixed_price=" + fixed_price + ", dd_price=" + dd_price
				+ ", keyset=" + keyset + ", product_pciture=" + product_pciture + ", deleteState=" + deleteState + "]";
	}
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
    <!-- 类以及对应的表 -->
    <class name="aaaaa.bean.Product" table="d_product">
        <!-- 配置主键 -->
        <id name="id" column="id">
            <!-- 配置自动增长 -->
            <generator class="native"></generator>
        </id>
        <!-- 配置其他字段 对象属性--表字段 -->
        <property name="product_name" column="product_name" />
        <property name="description" column="description" />
        <property name="add_time" column="add_time" />
        <property name="count" column="count" />
        <property name="fixed_price" column="fixed_price" />
        <property name="dd_price" column="dd_price" />
        <property name="keyset" column="keyset" />
        <property name="product_pciture" column="product_pciture" />
        <property name="deleteState" column="deleteState" />
    </class>
</hibernate-mapping>
```

## 测试

```java
	@Test
	public void test1(){
		//1.加载配置文件
		Configuration configure = new Configuration().configure();
		SessionFactory sessionFactory = configure.buildSessionFactory();
		//3.获取session (与数据库的会话)
		Session session = sessionFactory.openSession();
		//4.获取一个查询接口实现
		Query query = session.createQuery("from Product");   // HQL语句： Hibernate Query Lanauage
		List<Product> list = query.list();
		for (Product p : list) {
			System.out.println(p);
		}
		session.close();
	}
```
