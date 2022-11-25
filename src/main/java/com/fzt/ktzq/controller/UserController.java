package com.fzt.ktzq.controller;


import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//证明是controller层并且返回json
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public String getAll(){
//        List<User> all = userService.getAll();
        return "all";
    }

    @GetMapping("/hello")
    public List<User> hello()
    {
        List<User> all = userService.findAll();
        return all;
    }


}
