package com.hibian.web;

import com.hibian.bean.User;
import com.hibian.servce.UserServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserWeb {
    @Autowired
    UserServce us;

    @RequestMapping("/login")
    public String login(User  user){
        User login = us.login(user);
        if (login!=null){
            return "index";
        }
        return "login";
    }
}
