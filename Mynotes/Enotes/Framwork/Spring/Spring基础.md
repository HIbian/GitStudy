# Spring基础

## Rod Johnson

Spring Framework创始人，著名作者。 Rod在悉尼大学不仅获得了计算机学位，同时还获得了音乐学位。更令人吃惊的是在回到软件开发领域之前，他还获得了音乐学的博士学位。 有着相当丰富的C/C++技术背景的Rod早在1996年就开始了对Java服务器端技术的研究。他是一个在保险、电子商务和金融行业有着丰富经验的技术顾问，同时也是JSR-154（Servlet2.4）和JDO2.0的规范专家、JCP的积极成员，是Java development community中的杰出人物。

## Spring介绍

* 开放源码
* 高内聚低耦合
* 基于IOC和AOP架构JavaEE

## Spring作用
* 方便**解耦**，简化开发：创建对象，**维护依赖关系**
* AOP编程：**面向切面编程**,权限拦截，运行监控
* **声明式事务**支持：通过配置完成对事务的支持
* 方便**集成**其他框架

## Spring组成
![](img/1.png)

![](img/2.png)

## IOC-控制反转

* 用户不用自己创建对象，交给sprin框架创建

## DI-依赖注入

* 创建对象实例时，同时对这个对象注入所依赖的属性
  * **给对象属性赋值的过程叫做依赖注入**

## 创建项目

### 通过maven导入spring所依赖的包
```xml
<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>4.3.8.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>4.3.8.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>4.3.8.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
			<version>4.3.8.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-expression</artifactId>
			<version>4.3.8.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
			<version>1.1.2</version>
		</dependency>

		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.14</version>
		</dependency>

	</dependencies>
```

### 创建配置文件applicationContext.xml

**创建实体类Person**

```java
public class Person {
    private Integer id;
    private String name;
    private Integer age;
    ...
}
```
**管理对象**
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

       <bean id="person_id" name="person_name" class="com.hibian.bean.Person" />
</beans>
```

### 获取对象
```java
import com.hibian.bean.Person;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class testone {
    @Test
    public void test1(){//通过id
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) applicationContext.getBean("person_id");
        System.out.println(person);
    }
    @Test
    public  void  test2(){//全路径加载xml，通过id获取bean
        ApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("G:\\IDEAprojects\\testSpring\\src\\main\\resources\\applicationContext.xml");
        Person person = (Person)fileSystemXmlApplicationContext.getBean("person_id");
        System.out.println(person.toString());
    }
    @Test
    public  void  test3(){//通过name获取bean
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) ac.getBean("person_name");
        System.out.println(person.toString());
    }
    @Test
    public  void  test4(){//通过.class不用强转类型了 by name
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = ac.getBean("person_name", Person.class);
        System.out.println(person.toString());
    }
    @Test
    public  void  test5(){//通过.class不用强转类型了 by id
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = ac.getBean("person_id", Person.class);
        System.out.println(person.toString());
    }
    @Test
    public  void  test6(){//通过.class不用强转类型了 by class
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = ac.getBean(Person.class);
        System.out.println(person.toString());
    }
    @Test
    public  void  test7(){//通过beanFactory--已过时
        BeanFactory bf = new XmlBeanFactory(new FileSystemResource("G:\\IDEAprojects\\testSpring\\src\\main\\resources\\applicationContext.xml"));
        Person person = bf.getBean("person_id", Person.class);
        System.out.println(person.toString());
    }
}
```
### 配置文件详解

bean标签对应属性
![](img/3.png)

**scope属性**
* 默认为`scope="singleton"`创建的对象是单例
* `scope="prototype"`每次创建新的对象
