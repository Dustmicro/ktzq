package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.CheckPwd;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.service.UserService;
import com.fzt.ktzq.util.AuthUserContext;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 修改密码控制类
 * @author 黄弋峰 2022/12/21
 */
@RestController
public class ChangePwdController {
    private static final Logger logger = LoggerFactory.getLogger(ChangePwdController.class);

    @Autowired
    UserService userService;

    /**
     * 修改密码服务
     * @param checkPwd
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public RestResult<Object> changePwd(@RequestBody CheckPwd checkPwd) throws ServiceException{
        logger.info("修改密码服务开始，请求参数，{}", checkPwd);
        User user = AuthUserContext.getUser();
        Assert.notNull(checkPwd.getOldPwd(), "原密码不可为空！！");
        Assert.notNull(checkPwd.getNewPwd(), "新密码不可为空！！");
        Assert.notNull(checkPwd.getNewPwdRet(), "重复密码不可为空！！");
        String newPwd = checkPwd.getNewPwd();
        String oldPwd = checkPwd.getOldPwd();
        if (user.getPassword().equals(oldPwd)){
            if (newPwd.equals(oldPwd)){
                return RestResult.failure("-1", "新密码不能和原密码一致！！");
            }
            if (!newPwd.equals(checkPwd.getNewPwdRet())){
                return RestResult.failure("-1", "两次密码不一致！！");
            } else {
                //修改密码
                user.setPassword(newPwd);
                user.setUpdateTime(new Date());
                userService.updateUser(user);
                return RestResult.success("密码修改成功！！");
            }
        } else {
            logger.info("原密码不正确！！");
            return RestResult.failure("-3", "原密码不正确！！");
        }
    }
}
