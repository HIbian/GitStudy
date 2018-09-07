CREATE TABLE STUDENT
(
	SNO VARCHAR(3) NOT NULL,
	SNAME VARCHAR(4) NOT NULL,
	SSEX VARCHAR(2) NOT NULL,
	SBIRTHDAY DATETIME,
	CLASS VARCHAR(5)
)

CREATE TABLE COURSE
(
	CNO VARCHAR(5) NOT NULL,
	CNAME VARCHAR(10) NOT NULL,
	TNO VARCHAR(10) NOT NULL
)

CREATE TABLE SCORE
(SNO VARCHAR(3) NOT NULL,
CNO VARCHAR(5) NOT NULL,
DEGREE NUMERIC(10, 1) NOT NULL)

CREATE TABLE TEACHER
(TNO VARCHAR(3) NOT NULL,
TNAME VARCHAR(4) NOT NULL, TSEX VARCHAR(2) NOT NULL,
TBIRTHDAY DATETIME NOT NULL, PROF VARCHAR(6),
DEPART VARCHAR(10) NOT NULL)

INSERT INTO STUDENT (SNO,SNAME,SSEX,SBIRTHDAY,CLASS) VALUES (108 ,'曾华' ,'男' ,'1977-09-01',95033);
INSERT INTO STUDENT (SNO,SNAME,SSEX,SBIRTHDAY,CLASS) VALUES (105 ,'匡明' ,'男' ,'1975-10-02',95031);
INSERT INTO STUDENT (SNO,SNAME,SSEX,SBIRTHDAY,CLASS) VALUES (107 ,'王丽' ,'女' ,'1976-01-23',95033);
INSERT INTO STUDENT (SNO,SNAME,SSEX,SBIRTHDAY,CLASS) VALUES (101 ,'李军' ,'男' ,'1976-02-20',95033);
INSERT INTO STUDENT (SNO,SNAME,SSEX,SBIRTHDAY,CLASS) VALUES (109 ,'王芳' ,'女' ,'1975-02-10',95031);
INSERT INTO STUDENT (SNO,SNAME,SSEX,SBIRTHDAY,CLASS) VALUES (103 ,'陆君' ,'男' ,'1974-06-03',95031);
GO
INSERT INTO COURSE(CNO,CNAME,TNO)VALUES ('3-105' ,'计算机导论',825)
INSERT INTO COURSE(CNO,CNAME,TNO)VALUES ('3-245' ,'操作系统' ,804);
INSERT INTO COURSE(CNO,CNAME,TNO)VALUES ('6-166' ,'数据电路' ,856);
INSERT INTO COURSE(CNO,CNAME,TNO)VALUES ('9-888' ,'高等数学' ,100);
GO
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (103,'3-245',86);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (105,'3-245',75);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (109,'3-245',68);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (103,'3-105',92);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (105,'3-105',88);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (109,'3-105',76);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (101,'3-105',64);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (107,'3-105',91);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (108,'3-105',78);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (101,'6-166',85);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (107,'6-106',79);
INSERT INTO SCORE(SNO,CNO,DEGREE)VALUES (108,'6-166',81);
GO
INSERT INTO TEACHER(TNO,TNAME,TSEX,TBIRTHDAY,PROF,DEPART) 
VALUES (804,'李诚','男','1958-12-02','副教授','计算机系');
INSERT INTO TEACHER(TNO,TNAME,TSEX,TBIRTHDAY,PROF,DEPART) 
VALUES (856,'张旭','男','1969-03-12','讲师','电子工程系');
INSERT INTO TEACHER(TNO,TNAME,TSEX,TBIRTHDAY,PROF,DEPART)
VALUES (825,'王萍','女','1972-05-05','助教','计算机系');
INSERT INTO TEACHER(TNO,TNAME,TSEX,TBIRTHDAY,PROF,DEPART) 
VALUES (831,'刘冰','女','1977-08-14','助教','电子工程系');

INSERT INTO COURSE(CNO,CNAME,TNO)VALUES ('3-105' ,'计算机导论',825);
INSERT INTO COURSE(CNO,CNAME,TNO)VALUES ('3-245' ,'操作系统' ,804);
INSERT INTO COURSE(CNO,CNAME,TNO)VALUES ('6-166' ,'数据电路' ,856);
INSERT INTO COURSE(CNO,CNAME,TNO)VALUES ('9-888' ,'高等数学' ,100);

create table grade(
	low TINYINT,
	upp TINYINT,
	rank char(1)
);
insert into grade values(90,100,'A');
insert into grade values(80,89,'B');
insert into grade values(70,79,'C');
insert into grade values(60,69,'D');
insert into grade values(0,59,'E');


1、 查询Student表中的所有记录的Sname、Ssex和Class列。
SELECT sname, ssex, class from student;

2、 查询教师所有的单位即不重复的Depart列。
select DISTINCT(depart) from teacher;

3、 查询Student表的所有记录。
SELECT * from student;

4、 查询Score表中成绩在60到80之间的所有记录。
select * from score where degree BETWEEN 60 and 80;

5、 查询Score表中成绩为85，86或88的记录。
select * from score where degree in(85,86,88)

6、 查询Student表中“95031”班或性别为“女”的同学记录。
select * from student where ssex = '女' or class='95031';

7、 以Class降序查询Student表的所有记录。
desc、asc

8、 以Cno升序、Degree降序查询Score表的所有记录。
oreder by cno asc, degree desc;

9、 查询“95031”班的学生人数。
count(*) where class = '95031'

10、查询Score表中的最高分的学生学号和课程号。
max(degree)

11、查询‘3-105’号课程的平均分。
SELECT avg(degree) as 平均分 FROM score where cno = '3-105'

12、查询Score表中至少有5名学生选修的并以3开头的课程的平均分数。
select cno, COUNT(cno) as a, avg(degree) as 平均分 from score where cno like '3%' GROUP by cno HAVING a >= 5;

13、查询最低分大于70，最高分小于90的Sno列。
SELECT sno, min(degree) as mins, max(degree) as maxs from score GROUP BY sno HAVING mins > 70 and maxs < 90;

14、查询所有学生的Sname、Cno和Degree列。
select student.sname, score.cno, score.degree from student, score where student.sno = score.sno;
select student.sname, score.cno, score.degree from student inner join score on student.sno = score.sno;

15、查询所有学生的Sno、Cname和Degree列。
select score.sno, course.cname, score.degree from score, course where score.cno = course.cno

SELECT score.sno, course.cname, score.degree from score inner join course on score.cno = course.cno

16、查询所有学生的Sname、Cname和Degree列。
SELECT student.sname, course.cname, score.degree from student, course, score WHERE course.cno = score.cno and student.sno = score.sno;


SELECT student.sname, course.cname, score.degree from student INNER JOIN course on course.cno = score.cno INNER JOIN

17、查询“95033”班所选课程的平均分。
第一种：子查询：
select sno from student where class = '95033';
select avg(degree) from score where sno in (select sno from student where class = '95033');

第二种：联合查询
SELECT st.class, avg(s.degree) as 平均成绩 from student st, score s where st.sno = s.sno and st.class = '95033';

第三种：left join
select st.class, avg(degree) as 平均成绩 from score s left join student st on s.sno = st.sno where st.class = '95033';

18、现查询所有同学的Sno、Cno和rank列。
case when 条件 then ""
	   when 条件 then ""
else "" end

SELECT score.sno, score.cno, 
	(case when score.degree >= 90 then 'A'
			 when score.degree >= 80 and score.degree < 90 THEN 'B'
			 when score.degree >= 70 and score.degree < 80 THEN 'C'
			 when score.degree >= 60 and score.degree < 70 THEN 'D'
			 else 'E' END) as rank
from score;

19、查询选修“3-105”课程的成绩高于“109”号同学成绩的所有同学的记录。
select degree from score where sno = '109' and cno = '3-105';
select * from score where degree > (select degree from score where sno = '109' and cno='3-105') and cno = '3-105'

20、查询score中选学一门以上课程的同学中分数为非最高分成绩的记录。
select cno, max(degree) from score GROUP BY cno;
select sno, count(cno) as co from score group by sno having co >= 2;


21、查询成绩高于学号为“109”、课程号为“3-105”的成绩的所有记录。
select degree from score where sno = '109' and cno='3-105'

22、查询和学号为108的同学同年出生的所有学生的Sno、Sname和Sbirthday列。
select DATE_FORMAT(sbirthday,"%Y") from student where sno = "108";
select sno, sname, sbirthday from student where DATE_FORMAT(sbirthday,"%Y") = (select DATE_FORMAT(sbirthday,"%Y") from student where sno = '108');

SELECT DATE_FORMAT(NOW(), "%Y");

23、查询“张旭“教师任课的学生成绩。
select t.tname, st.sname, s.sno, c.cno, s.degree from teacher t, course c, score s, student st
	where st.sno = s.sno and t.tno = c.tno and c.cno = s.cno and t.tname = '张旭'


select tr.tname, st.sname, c.cno, s.degree from score s LEFT JOIN student st on s.sno = st.sno LEFT JOIN course c on c.cno = s.cno LEFT JOIN teacher tr on tr.tno = c.tno
	where tr.tname = '张旭'


select score.sno, score.cno, score.degree from score, course, teacher
	where score.cno = course.cno and course.tno = teacher.tno and teacher.tname='张旭'

select s.sno, c.cname, s.degree from teacher t LEFT JOIN course c on t.tno = c.tno LEFT JOIN score s on c.cno = s.cno
	where t.tname = '张旭'

select st.sname, c.cname, s.degree from teacher t LEFT JOIN course c on t.tno = c.tno LEFT JOIN score s on c.cno = s.cno LEFT JOIN student st on s.sno = st.sno
	where t.tname = '张旭'

select t.tname, sum(s.degree) as 总成绩 from teacher t LEFT JOIN course c on t.tno = c.tno LEFT JOIN score s on c.cno = s.cno
	WHERE c.cno is not null GROUP BY t.tname


24、查询选修某课程的同学人数多于5人的教师姓名。
select tc.tname,  count(s.sno) as 选课人数 
	from teacher tc, score s, course c 
	where tc.tno = c.tno and c.cno = s.cno GROUP BY s.cno HAVING 选课人数 > 5

select tc.tname,  count(s.sno) as 选课人数  from teacher tc LEFT JOIN course c on tc.tno = c.tno LEFT JOIN score s on s.cno = c.cno
	GROUP BY c.cno HAVING 选课人数 > 3;

SELECT t.tname, count(s.sno) as 选课人数 from teacher t LEFT JOIN course c on t.tno = c.tno LEFT JOIN score s on s.cno = c.cno
	GROUP BY t.tname having 选课人数 > 5

25、查询95033班和95031班全体学生的记录。
select * from student where class = '95033' or claas = '95031'
select * from student where class in ('95033','95031')
select * from student where sname like "%君"

26、查询存在有85分以上成绩的课程Cno.
select case when 大于85分的数量 > 0 then '存在' else '不存在' end from 
 (select 大于85分的数量 from (select count(*) as 大于85分的数量 from score where DEGREE > 85) as a) b;


select (case when count(*) > 1 then '存在' 
		else '不存在' end) as 是否有98分以上的科目
 from (select cno from score where degree >= 98) as a;

27、查询出“计算机系“教师所教课程的成绩表。
SELECT s.* from score s LEFT JOIN course c on s.cno = c.cno LEFT JOIN teacher tc on c.tno = tc.tno where tc.depart = '计算机系';



select * from teacher t LEFT JOIN course c on t.tno = c.tno LEFT JOIN score s on c.cno = s.cno
	where t.depart = '计算机系'


28、查询“计算机系”与“电子工程系“不同职称的教师的Tname和Prof。

29、查询选修编号为“3-105“课程且成绩至少高于选修编号为“3-245”的同学的Cno、Sno和Degree,并按Degree从高到低次序排序。
select DISTINCT(max(DEGREE)) from score where cno = '3-245';

select * from score where cno = '3-105' and DEGREE > (select DISTINCT(max(DEGREE)) from score where cno = '3-245');



31、查询所有教师和同学的name、sex和birthday.
select tname as name, tsex as sex, tbirthday as birthday from teacher
 union
select sname as name, ssex as sex, sbirthday as birthday from student;

select t.tname name, t.tsex sex, t.tbirthday from teacher t
UNION
SELECT s.sname name, s.ssex sex, s.sbirthday from student s;


32、查询所有“女”教师和“女”同学的name、sex和birthday.

33、查询成绩比该课程平均成绩低的同学的成绩表。
SELECT s.*, a.avgdegree from score s left join (select cno, avg(degree) avgdegree from score GROUP BY cno) as a 
	on s.cno = a.cno where s.degree < a.avgdegree

select s.*, b.avgDegree from score  s LEFT JOIN
 (select cno, avg(degree) as avgDegree from score GROUP BY cno) as b on s.cno = b.cno where s.degree < b.avgDegree;


34、查询所有任课教师的Tname和Depart.
select tc.tname, tc.depart from teacher tc LEFT JOIN course c on tc.tno = c.tno where c.cno is not null;

35 查询所有未讲课的教师的Tname和Depart. 
select tc.tname, tc.depart from teacher tc LEFT JOIN course c on tc.tno = c.tno where c.cno is null;

36、查询至少有2名男生的班号。
select class, count(ssex) as 男生数量 from student where ssex = '男' GROUP BY class having 男生数量 >= 2

37、查询Student表中不姓“王”的同学记录。


38、查询Student表中每个学生的姓名和年龄。
select sname, (DATE_FORMAT(NOW(),"%Y") - DATE_FORMAT(sbirthday,"%Y")) as 年龄 from student

39、查询Student表中最大和最小的Sbirthday日期值。

40、以班号和年龄从大到小的顺序查询Student表中的全部记录。


41、查询“男”教师及其所上的课程。
SELECT c.cname from course c LEFT JOIN teacher tc on c.tno = tc.tno where tc.tsex = '男'

42、查询最高分同学的Sno、Cno和Degree列。

43、查询和“李军”同性别的所有同学的Sname.


44、查询和“李军”同性别并同班的同学Sname.


45、查询所有选修“计算机导论”课程的“男”同学的成绩表
select s.* from score s LEFT JOIN course c on s.cno = c.cno LEFT JOIN student st on st.sno = s.sno where c.cname = '计算机导论' and st.ssex = '男'

select * from student st LEFT JOIN score s on st.sno = s.sno LEFT JOIN course c on s.cno = c.cno
	where c.cname = '计算机导论' and st.ssex = '男';
