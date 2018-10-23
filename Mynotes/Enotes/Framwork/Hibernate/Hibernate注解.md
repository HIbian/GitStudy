# Hibernate注解

## 配置一个表
**实体类**
```java
package bean;
import org.hibernate.annotations.ColumnDefault;
@Entity //表明是一个JPA实体类，可以使用JPA注解
@Table(name = "t_user") //table对应一个表
public class User {
    @Id //指定为主键，可以不配@Colum
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置主键的生成策略，自动增长
    private Integer u_id;
    @Column(name = "u_name",length = 20) //设置为列，name为数据表中的列名，默认和变量名相同
    private String u_name;
    @Column(length = 10)
    @ColumnDefault("18") //设置默认值，也可直接设置age=18；
    private Integer age;
    @Column(length = 100)
    private String address;
    @Transient //临时属性，不反映到数据库中
    public final static String version ="0.0.1";

    //TODO 构造方法，getter，setter，toString
}
```
**hibernate.xml配置**
添加`<mapping class="bean.User">`

## 自动增长策略

* IDENTITY： 利用数据库的自增长的能力。适合 mysql  SQLserver  数据自动增长
* SEQUENCE（序列）：利用数据库的序列机制，适合 Oracle
* TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。不同的JPA实现商生成的表名是不同的，如 OpenJPA生成openjpa_sequence_table表，Hibernate生成一个hibernate_sequences表，而TopLink则生成sequence表。这些表都具有一个序列名和对应值两个字段，如SEQ_NAME和SEQ_COUNT
* AUTO：自动选择一个最适合底层数据库的主键生成策略。这个是默认选项，即如果只写@GeneratedValue，等价于@GeneratedValue(strategy=GenerationType.AUTO)。

## 关联关系

### 一对多

### 多对多

### 一对一
