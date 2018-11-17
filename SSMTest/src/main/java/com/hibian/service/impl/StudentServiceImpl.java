package com.hibian.service.impl;

import com.hibian.bean.Student;
import com.hibian.mapper.StudentMapper;
import com.hibian.servce.StudentServce;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hibian
 * @since 2018-11-09
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentServce {

}
