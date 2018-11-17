package com.hibian.servce;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.hibian.bean.User;
import com.hibian.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServceimpl implements  UserServce{

    @Autowired
    UserMapper um;

    public User login(User user) {
//        EntityWrapper<User> entityWrapper = new EntityWrapper<User>();
//        entityWrapper.eq("username",user.getUsername()).eq("password",user.getPassword()).eq("del",user.getDel());
        return um.selectOne(user);
    }
}
