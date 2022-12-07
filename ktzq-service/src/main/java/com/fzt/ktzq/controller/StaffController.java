package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.*;
import com.fzt.ktzq.service.DeptService;
import com.fzt.ktzq.service.StaffService;
import com.fzt.ktzq.service.UserService;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.StringUtilsFzt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工服务控制类
 * @author 黄弋峰 2022/12/7
 */
@RestController
public class StaffController {
    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    StaffService staffService;

    @Autowired
    DeptService deptService;

    @Autowired
    UserService userService;

    /**
     * 查询所有员工数据
     * @return
     */
    @GetMapping("/selectStaffAll")
    public List<Staff> selectStaffAll(){
        return staffService.selectStaffAll();
    }

    /**
     * 根据条件查询员工
     * @param staff
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/selectStaff", method = RequestMethod.POST)
    public List<Staff> selectStaff(@RequestBody Staff staff) throws ServiceException {
        logger.info("查询员工服务开始，请求参数，{}", staff);
        List<Staff> list = new ArrayList<>();
        try {
            list = staffService.selectStaff(staff);
        } catch (Exception e){
            logger.info("查询员工异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询员工异常");
        }
        return list;
    }

    /**
     * 新增员工
     * @param staff
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/addStaff", method = RequestMethod.POST)
    public RestResult<Object> addStaff(@RequestBody Staff staff) throws ServiceException {
        logger.info("新增员工服务开始，请求参数，{}", staff);
        Assert.notNull(staff.getStaffName(), "员工名称不可为空");
        Assert.notNull(staff.getTel(), "员工电话不可为空");
        Assert.notNull(staff.getStaffRole(), "员工岗位不可为空");
        Assert.notNull(staff.getAddress(), "员工地址不可为空");
        Assert.notNull(staff.getSex(), "员工性别不可为空");
        Assert.notNull(staff.getParentDept(), "员工关联组织不可为空");
        try {
            Dept dept = new Dept();
            dept.setDeptId(staff.getDeptId());
            List<Dept> list = deptService.selectDept(dept);
            for (Dept name : list){
                staff.setParentDept(name.getDeptName());
            }
            Staff staffTel = new Staff();
            staffTel.setTel(staff.getTel());
            List<Staff> staffList = staffService.selectStaff(staffTel);
            if (StringUtilsFzt.isNotEmpty(staffList)){
                throw new ServiceException(CommConstant.ERROR_CODE, "已有电话号码为" + staff.getTel() + "的用户");
            }
            //同时将员工关联到人员表中
            User user = new User();
            user.setUserName(staff.getStaffName());
            user.setTel(staff.getTel());
            user.setPermissions(staff.getStaffRole());
            user.setAddress(staff.getAddress());
            user.setSex(staff.getSex());
            user.setAge(staff.getAge());
            user.setEMail(staff.getEmail());
            user.setPassword("000000");
            user.setAccount(staff.getTel());
            userService.insertUser(user);
            staff.setUserId(user.getUserId());
            staffService.addStaff(staff);
        } catch (Exception e){
            logger.info("查询员工异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询员工异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }
}
