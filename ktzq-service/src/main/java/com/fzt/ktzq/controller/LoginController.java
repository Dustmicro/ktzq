package com.fzt.ktzq.controller;

import com.alibaba.fastjson.JSON;
import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.ForgetPswVo;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.service.UserService;
import com.fzt.ktzq.util.AuthUserContext;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.StringUtilsFzt;
import com.fzt.ktzq.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求进拦截器之后，进登录控制器
 * @author 黄弋峰  2022/11/29
 */
@RestController
@Api//该注解有引入的依赖
public class LoginController{
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private static final String TEL_NOT_NULL = "电话不可为空！！";
    private static final String USER_PWD_ERR = "账号或密码错误！！";

    private static final String ACCOUNT = "account";

    private static final String USERNAME = "userName";
    private static final String SESSION_ID = "sessionId";
    private static final String VALIDATE_NUMB = "_validateNumb";

    @Autowired
    UserService userService;

//    @Autowired
//    CommService commService;

    @ApiOperation(value = "用户登录前获取令牌")
    @PostMapping(value = "/preLogin", produces = "application/json; charset=utf-8")
    public RestResult<Object> preLogin(@RequestBody Map<String, String> reMap){
        User dbUser = userService.findUserByUserName(reMap.get(USERNAME));
        Map<String, Object> map = new HashMap<>();
        //确实有这个用户才去响应
        if (dbUser != null){
//            String generatorVerifyCode = ValidateCode
            /**
             * 在这里作生成验证码操作
             */
            map.put("000000", CommConstant.SUCCESS);
            return RestResult.success(map);
        } else {
            return RestResult.failure(CommConstant.ERROR_CODE, USER_PWD_ERR);
        }
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    public RestResult<Object> login(@RequestBody Map<String, String> reqMap, HttpServletRequest req, HttpServletResponse rsp) {
        logger.info("用户登录，请求开始，用户名name,{}", reqMap.get(USERNAME));
        User dbUser = userService.findUserByUserName(reqMap.get(USERNAME));
        if (dbUser != null) {
//            Integer status = dbUser.getStatusCd();
            if ("0".equals(dbUser.getStatusCd())) {
                return RestResult.failure("-1", "用户状态异常，请联系管理员");
            }
            String psw = dbUser.getPassword();
            if (!psw.equals(reqMap.get("password"))){
                return RestResult.failure("-1", USER_PWD_ERR);
            }
            //生成token
            String token = TokenUtil.getToken(dbUser);
            rsp.setHeader("token", token);

            Map<String, Object> map = new HashMap<>();
            dbUser.setPassword(null);
            map.put("user", dbUser);
            String sessionId = req.getSession().getId();
            rsp.setHeader(SESSION_ID, sessionId);
            //返回给客户端保存
            req.getSession().setAttribute("userId", dbUser.getUserId());
            AuthUserContext.remove();
            return RestResult.success(map);
        }else {
            return RestResult.failure("-1", USER_PWD_ERR);
        }
    }

    @ApiOperation(value = "忘记密码")
    @PostMapping(value = "/forgetPsw", produces = "application/json; charset=utf-8")
    public RestResult<Object> forgetPsw(@RequestBody ForgetPswVo user, HttpServletRequest request){
        logger.info("忘记密码，请求开始，请求参数，{}", user);
        Assert.hasLength(user.getPassword(), "密码不可为空！！");
        Assert.hasLength(user.getPasswordRept(), "重复密码不可为空！！");
        Assert.hasLength(user.getTel(), TEL_NOT_NULL);
//        Assert.hasLength(user.getVerifyCode(), "验证码不可为空！！");
        if (!user.getPassword().equals(user.getPasswordRept())){
            logger.info("两次密码不一致，请重新输入");
            return RestResult.failure("-1", "两次密码不一致，请重新输入");
        }
        User userSelect = new User();
        userSelect.setTel(user.getTel());
//        User list = userService.selectUser(userSelect);
//        if (list == null){
//            logger.info("此电话的用户未找到！！");
//            return RestResult.failure("-1", "此用户不存在");
//        }
//        userSelect = list.get(0);
        userSelect.setUserId(user.getUserId());
        userSelect.setPassword(user.getPassword());
        userSelect.setUpdateTime(new Date());
        userSelect.setPswErrNum(0);
        userService.updateUser(userSelect);
        logger.info("密码设置成功");
        return RestResult.success(JSON.toJSONString("密码设置成功"));
    }

    @ApiOperation(value = "注册")
    @PostMapping(value = "/register", produces = "application/json; charset=utf-8")
    public RestResult<Object> register(@RequestBody User user) throws ServiceException{
        logger.info("注册服务开始，请求参数，{}", user);
        Assert.isNull(user.getUserName(), "用户名不可为空！！");
        Assert.isNull(user.getPassword(), "密码不可为空！！");
        Assert.isNull(user.getTel(), TEL_NOT_NULL);
        Assert.isNull(user.getEMail(), "邮箱不可为空！！");

        if (!user.getPassword().equals(user.getPasswordRept())){
            logger.info("两次密码不一致！！");
            return RestResult.failure("-1", "两次密码不一致");
        }

        //校验用户名
        User dbUser = new User();
        dbUser.setUserName(user.getUserName());
        List<User> list = userService.checkUser(dbUser);
        if (StringUtilsFzt.isNotEmpty(list)){
            logger.info("该用户名已被占用");
            return RestResult.failure(CommConstant.ERROR_CODE, "该用户名已被占用");
        }

        //校验邮箱
        dbUser.setUserName(null);
        dbUser.setEMail(user.getEMail());
        List<User> Email = userService.checkUser(dbUser);
        if (StringUtilsFzt.isNotEmpty(Email)){
            logger.info("该邮箱已被占用");
            return RestResult.failure(CommConstant.ERROR_CODE, "该邮箱已被占用");
        }

        //校验手机号
        dbUser.setEMail(null);
        dbUser.setTel(user.getTel());
        List<User> tel = userService.checkUser(dbUser);
        if (StringUtilsFzt.isNotEmpty(tel)){
            logger.info("该手机号已被占用");
            return RestResult.failure(CommConstant.ERROR_CODE, "改手机号已被占用");
        }
        int insertUser = userService.insertUser(dbUser);
        if (insertUser > 0){
            logger.info("注册成功！！");
            return RestResult.success(JSON.toJSONString("注册成功！！"));
        } else {
            logger.info("注册失败！！");
            return RestResult.failure(CommConstant.ERROR_CODE, "注册失败！！");
        }
    }
}
