package com.hibian.service.impl;

import com.hibian.bean.Course;
import com.hibian.mapper.CourseMapper;
import com.hibian.servce.CourseServce;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hibian
 * @since 2018-11-07
 */
@Service
@Transactional
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseServce {

}
