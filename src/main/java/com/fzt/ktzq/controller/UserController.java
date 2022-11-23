package com.fzt.ktzq.controller;

import com.fzt.ktzq.model.Building;
import com.fzt.ktzq.model.User;
import com.fzt.ktzq.service.UserService;
import com.fzt.ktzq.service.impl.UserServicelmpl;
import com.fzt.ktzq.util.JsonObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务类
 * @author 黄弋峰  2022/11/23
 */
@Api(tags = {""})
@RestController
@RequestMapping("/User")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 查询所有用户
     * @param page
     * @param limit
     * @param numbers
     * @return
     */
    @RequestMapping("/queryUserAll")
    public JsonObject queryUserAll(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "15") Integer limit,
                                    String numbers){
        JsonObject object=new JsonObject();
        PageInfo<User> pageInfo= userService.findUserAll(page,limit,numbers);
        object.setCode(0);
        object.setMsg("ok");
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;
    }

    @RequestMapping("/queryUser")
    public List<User> queryUser(){
        PageInfo<User> pageInfo= userService.findUserAll(1,100,null);
        return pageInfo.getList();
    }
}
