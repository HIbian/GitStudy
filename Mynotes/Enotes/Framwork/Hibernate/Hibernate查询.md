# Hibernate基础使用

## 新增
**save一个对象**
```java
	@Test
	public void testAdd(){
		Transaction tx = session.beginTransaction(); //开启事务
		try {
			Emp e=new Emp("猪八戒", "弼马温", 7369, new Date(), 2000.0, 200, 20);
      //----------------------------------------------------------------
			Serializable id = session.save(e); //新增数据库,返回生成的主键值----
      //----------------------------------------------------------------
			System.out.println("新增成功,主键为："+id);
			tx.commit();//提交事务
		} catch (Exception e) {
			tx.rollback();//回滚事务
			e.printStackTrace();
		}
	}
```
## 查询，根据主键查询
```java
  @Test
	public void testGetById(){
    //新版不能使用"？"占位 使用":name"占位，可以不用关系占位顺序
    // Query query = session.createQuery("from Emp where empno=:empno");
		// query.setParameter("empno", 7936);
		Query query = session.createQuery("from Emp where empno=?");
		query.setParameter(0, 7936);
    // 查询唯一的一条结果集  uniqueResult() 如果有多条将会报错！如果查询不到返回 null
		Emp e = (Emp) query.uniqueResult();
		System.out.println(e);
	}
	@Test
  public void testGetById2(){
   Emp e = session.get(Emp.class, 7936); //根据主键查询
   System.out.println(e);
  }

  @Test
  public void testGetById3(){
   Emp e = session.load(Emp.class, 7936); //根据主键查询
   System.out.println(e);
  }
```
## 更新
```java
	@Test
	public void testUpdate(){
		Transaction tx = session.beginTransaction();
		try {
      // 先根据主键获取要更新的对象，总之，跟新前需要一个有主键的对象
			Emp emp = session.get(Emp.class, 7936);
      // 更新字段
			emp.setEname("猪无能");
			emp.setDeptno(30);
			emp.setJob("天棚元帅");
			emp.setSal(5000.0);
      // 存入更新后的对象
			session.update(emp);
			tx.commit();
			System.out.println("更新成功~");
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

```

## 删除
```java
	@Test
	public void testDelete(){
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("delete from Emp where empno=?");
			query.setParameter(0, 7936);
			int count = query.executeUpdate();
			System.out.println(count); //返回受影响的行数
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
	@Test
	public void testDelete2(){
		Transaction tx = session.beginTransaction();
		try {
			Emp e=new Emp();
			e.setEmpno(7937);
			session.delete(e); //根据主键查询
			System.out.println("删除成功~~");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
	}
```

## 全查
```java
	@Test
	public void testQuery1(){
		List<Emp> list = session.createQuery("from Emp").list();  //适合查询少量的数据
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	@Test
	public void testQuery2(){
		Iterator<Emp> it = session.createQuery("from Emp").iterate(); //性能高
		while(it.hasNext()){
			Emp emp = it.next();
			System.out.println(emp);
		}
	}
```

## 投影查询
**查询指定的几列**
```java
  @Test
	public void testQuery3(){
		List<String> names = session.createQuery("select ename from Emp").list();
		for (String s : names) {
			System.out.println(s);
		}
	}

	@Test
	public void testQuery4(){
		List<Object[]> values = session.createQuery("select ename,sal,hiredate from Emp").list();
		for (Object[] o : values) {
			System.out.println(o[0]+"---"+o[1]+"---"+o[2]);
		}
	}

	@Test
	public void testQuery5(){
		// new Emp(ename,sal,hiredate) 调用Emp类的构造方法 让构造方法封装为对象
		List<Emp> list = session.createQuery("select new Emp(ename,sal,hiredate) from Emp").list();
		for (Emp e : list) {
			System.out.println(e);
		}
	}
```
## 聚合查询
* Max() 求最大值
* Min() 求最小值
* Avg() 求平均值
* Sum() 求综合
* Count() 求总数
```java
	/*
	 * 查询20号部门的最高薪资 最低薪资 平均薪资
	 */
	@Test
	public void testQuery6(){
		Query query = session.createQuery("select max(sal),min(sal),avg(sal) from Emp where deptno=?");
		query.setParameter(0, 20);
		Object[] result = (Object[]) query.uniqueResult();
		System.out.println(result[0]+"--"+result[1]+"--"+result[2]);
	}
```
## 分页查询

```java
@Test
	public void yesyQueryPage(){
		//1.获取数据总量
		Query query1 = session.createQuery("select count(empno) from Emp");
		int totalCount = Integer.parseInt(query1.uniqueResult().toString());

		//2.计算总页数
		int pageIndex=5; //当前页面
		int pageSize=3;  //每页大小

		int pageCount= (totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);

		//3.每页数据
		Query query2 = session.createQuery("from Emp");
    //------------------------------------------------------------------
		query2.setFirstResult((pageIndex-1)*pageSize); //设置开始查询的位置---
		query2.setMaxResults(pageSize); //设置每次查询的个数------------------
		//------------------------------------------------------------------
		List<Emp> list = query2.list();
		for (Emp emp : list) {
			System.out.println(emp);
		}

		System.out.println("当前页码:"+pageIndex);
		System.out.println("每页大小:"+pageSize);
		System.out.println("数据总量:"+totalCount);
		System.out.println("总页数:"+pageCount);
	}

```
