package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.AppMidRequestHeader;
import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.*;
import com.fzt.ktzq.service.UserService;
import com.fzt.ktzq.util.CommConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 黄弋峰 2023/1/3
 */
@RestController
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserService userService;

    /**
     * 限制登录服务
     * @param user
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "限制登录")
    @RequestMapping(value = "/limitLogin", method = RequestMethod.POST)
    public RestResult<Object> limitLogin(@RequestBody User user) throws ServiceException {
        logger.info("限制登录服务开始，请求参数，{}", user);
        try {
            //将status设置为“3”锁定账户
            user.setStatus("3");
            userService.updateUser(user);
        } catch (Exception e){
            logger.error("限制登录异常", e);
            throw new ServiceException(CommConstant.ERROR_CODE, "限制登录异常");
        }
        return RestResult.success("限制登录成功");
    }

    /**
     * 恢复登录
     * @param user
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "恢复登录")
    @RequestMapping(value = "/restoreLogin", method = RequestMethod.POST)
    public RestResult<Object> restoreLogin(@RequestBody User user) throws ServiceException {
        logger.info("恢复登录服务开始，请求参数，{}", user);
        try {
            //将status设置为“1”解锁账户
            user.setStatus("1");
            userService.updateUser(user);
        } catch (Exception e){
            logger.error("恢复登录异常", e);
            throw new ServiceException(CommConstant.ERROR_CODE, "恢复登录异常");
        }
        return RestResult.success("恢复登录成功");
    }

    /**
     * 管理员迷糊查询
     * @param admin
     * @param header
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "管理员模糊查询")
    @RequestMapping(value = "/findAdmin", method = RequestMethod.POST)
    public RspPage<Admin> findAdmin(@RequestBody Admin admin, AppMidRequestHeader header) throws ServiceException {
        try {
            PageMethod.startPage(header.getPageNo(), header.getPageSize());
            List<Admin> list = userService.selectAdmin(admin);
            Page<Admin> page = (Page<Admin>) list;
            return RspPage.getRspPage(page);
        } catch (Exception e){
            logger.error("查询管理员异常", e);
            throw new ServiceException(CommConstant.ERROR_CODE, "查询管理员异常");
        }
    }

    /**
     * 管理员隶属球队查询
     * @param admin
     * @param header
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "管理员隶属球队查询")
    @RequestMapping(value = "/findAdminForQD", method = RequestMethod.POST)
    public RspPage<Admin> findAdminForQD(@RequestBody Admin admin, AppMidRequestHeader header) throws ServiceException {
        try {
            PageMethod.startPage(header.getPageNo(), header.getPageSize());
            List<Admin> list = userService.selectAdmin(admin);
            Page<Admin> page = (Page<Admin>) list;
            return RspPage.getRspPage(page);
        } catch (Exception e){
            logger.error("查询管理员隶属球队异常", e);
            throw new ServiceException(CommConstant.ERROR_CODE, "查询管理员隶属球队异常");
        }
    }
}
