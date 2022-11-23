package com.fzt.ktzq.controller;

import com.fzt.ktzq.model.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserControllerTest {

    @Test
    public void queryUserAll() throws Exception {
        UserController userController = new UserController();

        System.out.println(userController.queryUserAll(1, 10, "1"));
    }
}