# MyBatis关联映射

* 要点
  * ResultMap
  * 一对一
  * 多对一,一对多
  * 多对多

# ResultMap

resultMap元素是 MyBatis中最重要最强大的元素。它就是让你远离90%的需要从结果 集中取出数据的JDBC代码的那个东西，而且在一些情形下允许你做一些 JDBC 不支持的事 情。
有朋友会问，之前的示例中我们没有用到结果集，不是也可以正确地将数据表中的数据映射到Java对象的属性中吗？是的。这正是resultMap元素设计的初衷，就是简单语句不需要明确的结果映射，而很多复杂语句确实需要**描述它们的关系**。

# 一对一

实体类

```java
public class Person2 {
    private Integer id;
    private String name;
}
public class Card {
    private Integer id;
    private String card_no;
    //外键,数据库里为person_id
    private Person2 person;
  }
```

配置文件

```xml
<!-- 方式一,写在一个sql语句中 -->
<select id="getCardById" resultMap="CardClass">
    select t_card.id,t_card.card_no,t_person.id p_id,t_person.name  from t_card,t_person where t_card.person_id=t_person.id and t_person.id=#{id}
</select>
<resultMap id="CardClass" type="Card">
    <id property="id" column="id"/><!-- 配置主键 属性名,列名 注意列名不要有冲突 -->
    <result property="card_no" column="card_no"/><!-- 一般的列名 -->
    <association property="person" javaType="Person2"><!-- 一对一关系外键 javaType为外键实体类对象类型 -->
      <!-- 配置实体类里面的属性,列名从查询结果中得来 -->
        <id property="id" column="p_id"/>
        <result property="name" column="name"/>
    </association>
</resultMap>

<!-- 方式二,写在两个sql语句中 -->
<select id="getCardById2" resultMap="CardClass2">
    select * from t_card where person_id=#{id}
</select>
<resultMap id="CardClass2" type="Card">
    <id property="id" column="id"/>
    <result property="card_no" column="card_no"/>
    <!-- 列名的值作为参数传入下一个select中.通过指定select获取对象 -->
    <association property="person" column="person_id" select="getPersonById"/>
</resultMap>
<!--suppress MybatisMapperXmlInspection -->
<select id="getPersonById" resultType="Person2">
    select * from t_person where id=#{person_id}
</select>
```

# 一对多,多对一

一对多用collection标签

```xml
<select id="getOrderByCustomerId" resultMap="tOrders">
    select * ,t_customer.id as tid,t_customer.name as tname from t_order,t_customer where t_order.customer_id=t_customer.id and t_order.customer_id=#{id}
</select>
<resultMap id="tOrders" type="t_Order">
    <id property="id" column="id"/>
    <result property="name" column="name"/>
    <result property="orderno" column="orderno"/>
    <association property="customer" javaType="t_Customer">
        <id property="id" column="tid"/>
        <result property="name" column="tname"/>
        <result property="gender" column="gender"/>
        <result property="level" column="level"/>
        <result property="weight" column="weight"/>
    </association>
</resultMap>

<select id="getOrderByCustomerId2" resultMap="tOrders2">
      select * from t_order where customer_id =#{id}
</select>
<resultMap id="tOrders2" type="t_Order">
    <id property="id" column="id"/>
    <result property="name" column="name"/>
    <result property="orderno" column="orderno"/>
    <association property="customer" column="customer_id" select="getCustomerById"/>
</resultMap>
<!--suppress MybatisMapperXmlInspection -->
<select id="getCustomerById" resultType="t_Customer">
    select * from t_customer where id=#{customer_id}
</select>

<select id="getCustomerByIdReally" resultMap="CustomerClass">
    select t1.*,t2.id as oid,t2.name as oname,t2.customer_id,t2.orderno
        from t_customer as t1,t_order as t2
            where t1.id=t2.customer_id and  t1.id=#{id};
</select>
<resultMap id="CustomerClass" type="t_Customer">
    <id property="id" column="id"/>
    <result property="name" column="name"/>
    <result property="gender" column="gender"/>
    <result property="weight" column="weight"/>
    <result property="level" column="level"/>
    <collection property="orders" ofType="t_Order">
        <id property="id" column="oid"/>
        <result property="name" column="oname"/>
        <result property="orderno" column="orderno"/>
    </collection>
</resultMap>

<select id="getCustomerByIdReally2" resultMap="CustomerClass2">
    select *
    from t_customer where id = #{id};
</select>
<resultMap id="CustomerClass2" type="t_Customer">
    <id property="id" column="id"/>
    <result property="name" column="name"/>
    <result property="gender" column="gender"/>
    <result property="weight" column="weight"/>
    <result property="level" column="level"/>
    <collection property="orders" ofType="t_Order" column="id" select="getOrderByCustomerIdReally"/>
</resultMap>
<!--suppress MybatisMapperXmlInspection -->
<select id="getOrderByCustomerIdReally" resultType="t_Order">
    select *
    from t_order where customer_id = #{id};
</select>
```

# 多对多

三张表,推荐使用写一个sql语句的方法

```xml
<select id="getUserById" resultMap="UserClass">
    select u_id,u_name,r_name,r_id
    from t_user,t_role,t_user_role where u_id = user_id and r_id = role_id and u_id = #{id};
</select>
<resultMap id="UserClass" type="User">
    <id property="u_id" column="u_id"/>
    <result property="name" column="u_name"/>
    <collection property="roles" ofType="Role">
        <id property="r_id" column="r_id"/>
        <result property="r_name" column="r_name"/>
    </collection>
</resultMap>

<select id="getUserById2" resultMap="UserClass2">
    select * from t_user where u_id = #{id}
</select>
<resultMap id="UserClass2" type="User">
    <id property="u_id" column="u_id"/>
    <result property="name" column="u_name"/>
    <collection property="roles" column="u_id" ofType="Role" select="getRolesById"/>
</resultMap>
<!--suppress MybatisMapperXmlInspection -->
<select id="getRolesById" resultType="Role">
    select * from t_role where r_id in (select role_id from t_user_role where user_id=#{u_id})
</select>
```
