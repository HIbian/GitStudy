package com.hibian.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hibian.Utils.pageListHelper;
import com.hibian.bean.Course;
import com.hibian.bean.Grade;
import com.hibian.servce.CourseServce;
import com.hibian.servce.GradeServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hibian
 * @since 2018-11-07
 */
@Controller
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradeServce gs;
    @Autowired
    CourseServce cs;

    @RequestMapping("list/{pageIndex}")
    public String list(@PathVariable("pageIndex") Integer pageIndex, Integer pageSize, Model model){

        pageIndex = pageIndex==null?1:pageIndex;
        pageSize = pageSize==null?10:pageSize;

        Page<Grade> gradePage = new Page<>(pageIndex, pageSize);
        Wrapper<Grade> gradeWrapper = new EntityWrapper<Grade>().eq("del", 0);

        Page<Grade> selectPage = gs.selectPage(gradePage, gradeWrapper);//结果页面,存放页面信息
        model.addAttribute("selectPage",selectPage);

        List<Grade> records = selectPage.getRecords();
        for (Grade g:records) {
            Course course = cs.selectOne(new EntityWrapper<Course>().eq("id", g.getCid()));
            g.setCourse(course);
        }
        model.addAttribute("pageBean",records);//分完页完整的结果列表

        int[] pageIndexList = pageListHelper.getPageIndexList(pageIndex, pageSize, ((Long) selectPage.getTotal()).intValue());
        model.addAttribute("pageList",pageIndexList);

        return "gradelist";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id") int id){
        Grade grade = gs.selectOne(new EntityWrapper<Grade>().eq("id", id));
        grade.setDel(1);
        gs.updateById(grade);
        return "redirect:/grade/list/1";
    }

    @RequestMapping("/goupdate/{id}")
    public String goupdate(@PathVariable int id,Model model){
        Grade grade = gs.selectOne(new EntityWrapper<Grade>().eq("id", id));
        model.addAttribute("g",grade);
        return "gradeupdate";
    }
    @RequestMapping("/realupdate")
    public String realupdate(Grade g){
        gs.updateById(g);
        return "redirect:/grade/list/1";
    }
    @RequestMapping("/add")
    public String add(Grade g){
        g.setDel(0);
        g.setCount(0);
        gs.insert(g);
        return "redirect:/grade/list/1";
    }
    @ResponseBody
    @RequestMapping("/getGradeLists")
    public List<Grade> getGradeLists(){
        List<Grade> list = gs.selectList(new EntityWrapper<Grade>().eq("del", 0));
        return list;
    }
}

