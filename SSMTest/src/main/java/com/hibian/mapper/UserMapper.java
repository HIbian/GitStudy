package com.hibian.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hibian.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //    User login(User user);//用户登陆验证用户名和密码
}
