package com.hibian.service.impl;

import com.hibian.bean.Grade;
import com.hibian.mapper.GradeMapper;
import com.hibian.servce.GradeServce;
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
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeServce {

}
