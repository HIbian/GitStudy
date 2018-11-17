package com.hibian.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hibian.Utils.pageListHelper;
import com.hibian.bean.Course;
import com.hibian.bean.Grade;
import com.hibian.servce.CourseServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseServce cs;

        @RequestMapping("/list/{page}")
        public String listpage(@PathVariable("page") Integer pageIndex,Integer pageSize, Model model){
            pageIndex=pageIndex==null?1:pageIndex;
            pageSize=pageSize==null?10:pageSize;
            Page<Course> page = new Page<>(pageIndex, pageSize);
            System.out.println("pageindex=" + pageIndex);
            System.out.println("pageSize="+pageSize);
            Page<Course> selectPage = cs.selectPage(page,new EntityWrapper<Course>().eq("del",0));
            model.addAttribute("pageBean",selectPage);

            int[] pageIndexList = pageListHelper.getPageIndexList(selectPage.getCurrent(), selectPage.getSize(), ((Long) selectPage.getTotal()).intValue());
            model.addAttribute("pagelist",pageIndexList);

            return "courselist";
    }
    @RequestMapping("/del/{cid}")
    public String del(@PathVariable("cid") int cid){
        Course course = cs.selectOne(new EntityWrapper<Course>().eq("id", cid));
        course.setDel(1);
        boolean b = cs.updateById(course);
        if (b){
            return "redirect:/course/list/1";
        }
        return "redirect:/course/list/1";
    }
    @RequestMapping("/goupdate/{id}")
    public String goupdate(@PathVariable int id,Model model){
        Course course = cs.selectOne(new EntityWrapper<Course>().eq("id", id));
        model.addAttribute("c",course);
        return "courseupdate";
    }
    @RequestMapping("/realupdate")
    public String realupdate(Course course){
        cs.updateById(course);
        return "redirect:/course/list/1";
    }
    @RequestMapping("/add")
    public String add(Course course){
        course.setDel(0);
        boolean insert = cs.insert(course);
        if (insert){
            return "redirect:list/1";
        }
        return "courseadd";
    }
    @ResponseBody
    @RequestMapping("/getcourseLists")
    public List<Course> getcourseLists(){
        List<Course> courses = cs.selectList(new EntityWrapper<Course>().eq("del", 0));
        return courses;
    }
}

