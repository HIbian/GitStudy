```sql
-- 登陆mysql
-- cmd 输入 mysql -u root -p
-- 创建数据库
CREATE DATABASE mydb1;
-- 查看数据库
SHOW DATABASES;
-- 删除数据库
DROP DATABASE mydb1;
-- 查看当前使用的数据库
SELECT DATABASE();
-- 切换数据库
USE mydb1;

-- DDL操作
-- 新建表
CREATE TABLE 表名(
字段1 字段类型(长度) 约束,
字段2 字段类型(长度) 约束,
...
字段n 字段类型(长度) 约束
);
-- 查看当前数据库中所有表
SHOW TABLES;
-- 查看表的字段信息（设计）
DESC 表名;
-- 添加一列
ALTER TABLE employee ADD 列名 类型 约束;
-- 修改列
ALTER TABLE employee MODIFY 列名 类型;
-- 删除列
ALTER TABLE employee DROP 列名;
-- 修改表名
RENAME TABLE employee TO 新名字;
-- 修改列名
ALTER TABLE emp CHANGE 旧列名 新列名 类型;

-- DML操作
-- INSERT UPDATE DELETE
INSERT INTO 表名(列参数) VALUES(值);
UPDATE 表名 SET 列名 = 修改后的值;
DELETE FROM 表名 (WHERE...);
TRUNCATE TABLE emp;
-- DELETE 删除表中的数据，表结构还在;删除后的数据可以找回
-- TRUNCATE 删除是把表直接DROP掉，然后再创建一个同样的新表。
-- 删除的数据不能找回。执行速度比DELETE快。

-- DQL数据查询 返回结果为一张虚表
SELECT 列名 FROM 表名 [WHERE->GROUP BY->HAVING->ORDER BY];
WHERE 条件
GROUP BY 对结果分组   ps:和聚合函数同时出现的列名，则一定要写在group by 之后
HAVING 对分组后的结果进行条件筛选
		注：having与where的区别:
		1.having是在分组后对数据进行过滤,where是在分组前对数据进行过滤
		2.having后面可以使用分组函数(统计函数)
		where后面不可以使用分组函数。
		WHERE是对分组前记录的条件，如果某行记录没有满足WHERE子句的条件，
		那么这行记录不会参加分组；而HAVING是对分组后数据的约束。
ORDER BY 排序结果

-- 模糊查询
LIKE _一个任意字符 %多个任意字符
-- 去重复
DISTINCT
eg
SELECT DISTINCT salary FROM emp;
-- 不同列 都为数值型，可以直接相加
SELECT *,sal+comm FROM emp;
-- IFNULL(列明,替换后的值) ifnull可将列中为null的值替换掉，方便统计
SELECT *,sal+IFNULL(comm,0) FROMemp;
-- 添加别名 as 也可以省略as
-- 排序 ORDER BY   ... ASC 小到大 DESC 大到小 多个使用逗号分开

-- 聚合函数
COUNT(expr);
MAX(expr);
MIN(expr);
SUM(expr);
AVG(expr);

-- LIMIT
LIMIT用来限定查询结果的起始行，以及总行数
SELECT * FROM emp LIMIT 0, 5;-- 只显示0到5行


-- 数据的完整性

-- 1，实体完整性
-- 主键约束primary key
-- 添加主键的三种方式
-- 第一种
CREATE TABLE testt(
	id INT PRIMARY KEY,
	age INT NOT NULL
);
-- 第二种
CREATE TABLE testt(
	id INT,
	age INT NOT NULL，
	PRIMARY KEY(id,age)
);
-- 第三种
CREATE TABLE testtttt(
	id INT ,
	age INT NOT NULL
);
ALTER TABLE testtttt ADD PRIMARY KEY(id);
-- 唯一约束unique
-- 数据唯一，不能为null
CREATE TABLE testttttttt(
	id INT PRIMARY KEY,
	sname VARCHAR(16),
	classid INT UNIQUE
);
-- 自动增长列auto_increment
CREATE TABLE test_auto_incre(
	id INT PRIMARY KEY auto_increment,
	sname VARCHAR(30),
	classid INT UNIQUE
);

-- 2，域完整性

-- 非空约束
CREATE TABLE test_not_null(
	id INT PRIMARY KEY auto_increment,
	sname VARCHAR(30) NOT NULL,
	classid INT UNIQUE
);
-- 默认值约束
CREATE TABLE test_default(
	id INT PRIMARY KEY auto_increment,
	sname VARCHAR(30) NOT NULL DEFAULT 'cx',
	classid INT UNIQUE
);
insert test_default values(3,DEFAULT,827);

-- 3，引用完整性（参照完整性）
-- 外键约束 FOREIGN KEY
CREATE TABLE student(
	sid int pirmary key,
	name varchar(50) not null,
	sex varchar(10) default '男'
);
create table score(
        id int,
        score int,
        sid int ,
        CONSTRAINT fk_score_sid foreign key(sid) references student(id)
);
-- 第二种添加方式
ALTER TABLE score ADD CONSTRAINT fk_stu_score FOREIGN KEY(sid) REFERENCES stu(id);

-- 存储过程
SET @age=18;
-- 创建存储过程
CREATE PROCEDURE INtest(in age INT)
BEGIN
SELECT age;
SET age =100;
SELECT age;
END;
-- 调用存储过程
CALL INtest(@age);
SELECT @age;
-- 删除存储过程
DROP PROCEDURE INtest;
-- ----------------------------------
SET @age=18;
SELECT @age;-- 18

CREATE PROCEDURE outtest(OUT age INT)
BEGIN
SELECT age;-- 值为null
SET age=30;-- 需要赋初值，否则为空
SELECT age;-- 30
END;

CALL outtest(@age);
SELECT @age;-- 30

DROP PROCEDURE outtest;
-- -----------------------------------
SET @age=20;
SELECT @age;-- 20

CREATE PROCEDURE inouttest(INOUT age int)
BEGIN
SELECT age;-- 20
SET age=333;
SELECT age;-- 333
END;

CALL inouttest(@age);
SELECT @age;-- 333

DROP PROCEDURE inouttest;
-- 分页显示数据 1-------------------
CREATE PROCEDURE Fenye(in rs INT,in rows INT)
BEGIN
	DECLARE n INT;
	SET n=0;
	WHILE n<rows DO
					SELECT * FROM student LIMIT n,rs;
					SET n=n+rs;
		END WHILE;
END;

SET @rs=5;
SET @rows =6;
CALL Fenye(@rs,@rows);

DROP PROCEDURE Fenye;
-- 分页显示数据 2--------------------
CREATE PROCEDURE getpage(in cur_index INT,in page_size INT)
BEGIN
	DECLARE current_index INT;
	SET current_index = cur_index*page_size;
	SELECT * FROM student LIMIT current_index,page_size;
END;

CALL getpage(1,4);

DROP PROCEDURE getpage;

```
