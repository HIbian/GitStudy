# MyBatis基础使用

* 要点
  * 导包,mysql,mybatis,log4j,junit4
  * 实体类<->数据库对应表
  * MyBatis-config.xml 配置
    * 数据库链接字段
    * 关联Mapper.xml
  * 抽象类<->Mapper.xml结合进行增删改查 sql语句写在Mapper.xml里
  * 测试代码

# pom.xml

```xml
<dependencies>
    <!-- myBatis -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.4.5</version>
    </dependency>

    <!-- mysql驱动包 -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.11</version>
    </dependency>

    <!-- Junit测试 -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>

    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.14</version>
    </dependency>
</dependencies>
```

# log4j jdbc 属性文件

```java
log4j.rootLogger=DEBUG, Console
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n

log4j.logger.java.sql.ResultSet=INFO
log4j.logger.org.apache=INFO
log4j.logger.java.sql.Connection=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```

```java
jdbc.url=jdbc:mysql://localhost:3306/mybatis1105?useSSL=false&serverTimezone=UTC&charset=UTF-8
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.username=root
jdbc.password=chenxin
```

# MyBatis-config.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--引入外部配置文件-->
    <properties resource="jdbc.properties"/>

    <!--给实体类配置别名-->
    <typeAliases>
        <!--不推荐使用这种,每个都要配,麻烦-->
        <!--<typeAlias type="com.hibian.bean.Person" alias="Person"/>-->
        <!--推荐这种,默认为类名,不区分大小写-->
        <package name="com.hibian.bean" />
    </typeAliases>

    <!--环境-->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}" />
                <property name="url" value="${jdbc.url}" />
                <property name="username" value="${jdbc.username}" />
                <property name="password" value="${jdbc.password}" />
            </dataSource>
        </environment>
    </environments>
    <!--映射Mapper文件-->
    <mappers>
        <mapper resource="Mapper.xml"/>
        <mapper resource="RelationMapper.xml"/>
    </mappers>
</configuration>

```

# 抽象方法与Mapper.xml

```java
public interface PersonMapper {
    List<Person> getList();
    void addPerson(Person person);
    void addPerson2(Person person);
    void deleteById(int i);
    Person getById(int id);
    List<Person> getList2(String name,String phone);
    List<Person> getList3(Map map);
    List<Person> getList4ByPage(@Param("offset") int offset, @Param("pagesize") int pagesize);//使用注解,占位符需要和注解配置的名字一样
}

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--关联的接口文件-->
<mapper namespace="com.hibian.mapper.PersonMapper">

    <select id="getList" resultType="Person"><!--Person为mybatis中配置的别名-->
        select *  from t_person
    </select>

    <insert id="addPerson" parameterType="Person" useGeneratedKeys="true" keyProperty="id"><!--返回自增的id到Person-->
        insert into t_person values (null ,#{name},#{age},#{sex},#{phone})
    </insert>

    <insert id="addPerson2" parameterType="Person"><!--返回自增的id到Person-->
        <selectKey keyProperty="id" resultType="int" order="AFTER">
            select last_insert_id()
        </selectKey>
        insert into t_person values (null ,#{name},#{age},#{sex},#{phone})
    </insert>

    <delete id="deleteById" parameterType="int">
        delete from t_person where id=#{id}
    </delete>

    <select id="getById" resultType="Person" parameterType="int">
        select * from t_person where id=#{i2d}
    </select>

    <select id="getList2"  resultType="Person">
        select * from t_person where name like '%${param1}%' and phone=#{param2}
    </select>

    <select id="getList3" resultType="Person" parameterType="map"><!--使用map传递参数-->
        select * from t_person where name like #{name} and phone=#{phone}
    </select>

    <select id="getList4ByPage" resultType="com.hibian.bean.Person">
        select * from t_person limit #{offset},#{pagesize}
    </select>
</mapper>
```

# 工具类
```java
package com.hibian.utils;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
* 工具类
* @author Administrator
*/
public class MyBatisUtils {
  private static SqlSessionFactory sqlSessionFactory;
  static {
      try {
          String resource = "mybatis-config.xml";
          InputStream inputStream;
          inputStream = Resources.getResourceAsStream(resource);
          sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  public static SqlSession getSession() {
      return sqlSessionFactory.openSession(true);
  }
  public static void close(SqlSession sqlSession) {
      if (sqlSession != null) {
          sqlSession.close();
          sqlSession = null;
      }
  }
}
```
