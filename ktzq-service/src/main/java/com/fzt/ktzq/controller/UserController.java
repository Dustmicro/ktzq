package com.fzt.ktzq.controller;


import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制类
 * @author 黄弋峰  2022/11/27
 */
@RestController//证明是controller层并且返回json
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有数据
     * @return
     */
    @GetMapping("/selectUserAll")
    public List<User> hello()
    {
        List<User> all = userService.findAll();
        return all;
    }


}
