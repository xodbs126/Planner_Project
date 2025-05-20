package com.example.planner_project.controller;


import com.example.planner_project.plan.Plan;
import com.example.planner_project.service.UserService;
import com.example.planner_project.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    static UserService userservice;

    @PostMapping("/api/register")
    public User register() {
        User user = new User(1, "TAE_YUN", "test123", "example@gmail.com");


    }

}
