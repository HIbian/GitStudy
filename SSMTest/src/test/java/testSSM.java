import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hibian.bean.Course;
import com.hibian.bean.Grade;
import com.hibian.bean.User;
import com.hibian.mapper.UserMapper;
import com.hibian.servce.CourseServce;
import com.hibian.servce.GradeServce;
import com.hibian.servce.UserServce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class testSSM {

    @Autowired
    UserServce us;

    @Autowired
    UserMapper um;

    @Test
    public void test1(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        User login = us.login(user);
        System.out.println(login);
    }
    @Test
    public void test2(){//新增
        User user = new User();
        user.setUsername("root");
        user.setPassword("123");
        user.setDel(0);
        user.setSalt("233");
        Integer insert = um.insert(user);
        System.out.println(insert);
        System.out.println(user.toString());
    }
    @Test
    public void test3(){
        User user = new User();
        user.setId(2);
        user.setUsername("root2");
        user.setPassword("1234");
        user.setLocked('0');
        Integer integer = um.updateById(user);
        System.out.println(integer);
        System.out.println(user.toString());
        List<User> users = um.selectList(new EntityWrapper<User>());
        System.out.println(users);
    }
    @Test
    public void test4(){//分页查询
        List<User> users = um.selectPage(new Page<>(1, 3),new EntityWrapper<User>().like("username","admin"));//页数,页面大小
        for(User u:users){
            System.out.println(u.toString());
        }
    }

    //测试自动生成的方法
    @Autowired
    CourseServce cs;
    @Autowired
    GradeServce gs;
    @Test
    public void test5(){//全查
        List<Course> courses = cs.selectList(null);
        for (Course c:courses){
            System.out.println(c.toString());
        }
        List<Grade> grades = gs.selectList(null);
        for (Grade g:grades){
            System.out.println(g.toString());
        }
    }
    @Test
    public void test6(){//分页查询
        Page<Course> page = new Page<>(4, 7);
        Page<Course> results = cs.selectPage(page);
        List<Course> records = results.getRecords();
        int i = ((Long)results.getTotal()).intValue();
        System.out.println("getOffset="+results.getOffset());
        System.out.println("getTotal="+i);
        System.out.println("getLomit="+results.getLimit());
        System.out.println("getCurrent="+results.getCurrent());
        System.out.println("getSize="+results.getSize());
        System.out.println("hasNext="+results.hasNext());
        System.out.println("hasPrevious="+results.hasPrevious());
        for (Course c:
             records) {
            System.out.println(c.toString());
        }
    }
}
