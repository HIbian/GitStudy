package com.hibian.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hibian.Utils.pageListHelper;
import com.hibian.bean.Grade;
import com.hibian.bean.Student;
import com.hibian.servce.GradeServce;
import com.hibian.servce.StudentServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hibian
 * @since 2018-11-09
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServce ss;
    @Autowired
    GradeServce gs;

    @RequestMapping("/list/{pageIndex}/{pageSize}")
    public String studentlist(@PathVariable Integer pageIndex, @PathVariable Integer pageSize,
                              Student student, Model model){
        pageIndex = pageIndex==null?1:pageIndex;
        pageSize = pageSize==null?10:pageSize;

        if (student.getGid() == null && student.getName()==null) {
            student.setName("");
            student.setGid(-1);
        }
        model.addAttribute("stu",student);

        Page<Student> studentPage = new Page<>(pageIndex, pageSize);
        Wrapper<Student> wrapper = new EntityWrapper<Student>().eq("del", 0).like("name", student.getName());
        if (student.getGid()!=-1){
            wrapper = wrapper.eq("gid", student.getGid());
        }
        Page<Student> selectPage = ss.selectPage(studentPage, wrapper);
        model.addAttribute("selectPage", selectPage);

        List<Student> records = selectPage.getRecords();
        for (Student s:records){
            Grade grade = gs.selectOne(new EntityWrapper<Grade>().eq("id", s.getGid()));
            s.setGrade(grade);
        }
        model.addAttribute("pageBean",records);

        int[] pageIndexList = pageListHelper.getPageIndexList(selectPage.getCurrent(), selectPage.getSize(), (int) selectPage.getTotal());
        model.addAttribute("pageList",pageIndexList);

        return  "studentlist";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id") Integer id){
        Student s = ss.selectOne(new EntityWrapper<Student>().eq("id", id));
        s.setDel(1);
        ss.updateById(s);
        return "redirect:/student/list/1/10";
    }

    @RequestMapping("/detial/{id}")
    public String detial(@PathVariable Integer id,Model model){
        Student s = ss.selectOne(new EntityWrapper<Student>().eq("id", id));
        Grade grade = gs.selectOne(new EntityWrapper<Grade>().eq("id", s.getGid()));
        s.setGrade(grade);
        model.addAttribute("s", s);
        return "studentdetails";
    }

    @RequestMapping("/goupdate/{id}")
    public String goupdate(@PathVariable Integer id,Model model){
        Student s = ss.selectOne(new EntityWrapper<Student>().eq("id", id));
        model.addAttribute("s",s);
        return "studentupdate";
    }

    @RequestMapping("/realupdate")
    public String realupdate(Student s){
        ss.updateById(s);
        return "redirect:/student/list/1/10";
    }

    @RequestMapping("/add")
    public String add(Student s){
        ss.insert(s);
        return "redirect:/student/list/1/10";
    }
}

