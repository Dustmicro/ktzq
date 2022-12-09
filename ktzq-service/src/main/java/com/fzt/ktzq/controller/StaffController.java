package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.*;
import com.fzt.ktzq.service.DeptService;
import com.fzt.ktzq.service.StaffService;
import com.fzt.ktzq.service.UserRoleMappingService;
import com.fzt.ktzq.service.UserService;
import com.fzt.ktzq.util.AuthUserContext;
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

    @Autowired
    UserRoleMappingService userRoleMappingService;

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
        Assert.notNull(staff.getRoleId(), "员工岗位不可为空");
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
            user.setRoleId(staff.getRoleId());
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

    /**
     * 修改员工服务
     * @param staff
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/updateStaff", method = RequestMethod.POST)
    public String updateStaff(@RequestBody Staff staff) throws ServiceException{
        logger.info("修改员工服务开始，请求参数，{}", staff);
        try {
            Staff staff1 = staffService.selectStaffById(staff.getStaffId());
            if (staff1 == null){
                throw new ServiceException(CommConstant.ERROR_CODE, "该用户不存在");
            }
            //不能修改自己的角色
            Staff userRole = new Staff();
            userRole.setUserId(AuthUserContext.getUser().getUserId());
            Staff staffRult = staffService.find(userRole);
            if (staffRult != null && staffRult.getStaffId().equals(staff.getStaffId()) && !staff1.getRoleId().equals(staff.getRoleId())){
                throw new ServiceException(CommConstant.ERROR_CODE, "不能修改自己的角色");
            }
            //校验电话重复
            Staff staff2 = new Staff();
            staff2.setTel(staff.getTel());
            if (staff1.getTel().equals(staff.getTel()) && staffService.find(staff2) != null){
                throw new ServiceException(CommConstant.ERROR_CODE, "已有电话为" + staff.getTel() + "的员工，请重新输入！！");
            }
            boolean i = staffService.updateStaff(staff);
            if (i = false){
                throw new ServiceException(CommConstant.ERROR_CODE, "员工信息修改失败！！");
            }
            //同时更新角色权限表
            UserRoleMapping param = new UserRoleMapping();
            param.setRoleId(staff.getRoleId());
            param.setUserId(staff.getUserId());
            userRoleMappingService.updateByUserId(param);
            //更新用户表
            User user = new User();
            user.setUserId(staff1.getUserId());
            user.setEMail(staff.getEmail());
            user.setTel(staff.getTel());
            user.setAddress(staff.getAddress());
            user.setUserName(staff.getStaffName());
            boolean a = userService.updateUser(user);
            if (a = false){
                throw new ServiceException(CommConstant.ERROR_CODE, "员工用户信息修改失败！！");
            }
        } catch (ServiceException e){
            logger.info("员工信息修改异常！！");
            throw new ServiceException(CommConstant.ERROR_CODE, e.getDesc());
        } catch (Exception e){
            logger.info("员工信息修改异常！！");
            throw new ServiceException(CommConstant.ERROR_CODE, "员工信息修改异常！！");
        }
        return CommConstant.SUCCESS;
    }

    /**
     * 修改员工信息
     * @param staff
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/deleteStaff", method = RequestMethod.POST)
    public String deleteStaff(@RequestBody Staff staff) throws ServiceException{
        logger.info("删除员工服务开始，请求参数，{}", staff);
        try {
            Staff result = staffService.selectStaffById(staff.getStaffId());
            if (result == null){
                throw new ServiceException(CommConstant.ERROR_CODE, "此员工信息不存在");
            }
            //同时删除用户
            User user = new User();
            user.setUserId(staff.getUserId());
            user.setStatusCd(CommConstant.TATA_DELETE_FLAG);
            boolean i = userRoleMappingService.deleteByUserId(result.getUserId());
            if (i = false){
                throw new ServiceException(CommConstant.ERROR_CODE, "删除用户信息失败！！");
            }
            staff.setStatusCd(CommConstant.TATA_DELETE_FLAG);
            boolean a = staffService.updateStaff(staff);
            if (a = false){
                throw new ServiceException(CommConstant.ERROR_CODE, "删除员工信息失败！！");
            }
        } catch (ServiceException e){
            logger.info("删除员工信息异常");
            throw new ServiceException(CommConstant.ERROR_CODE, e.getDesc());
        } catch (Exception e){
            logger.info("删除员工信息异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "删除员工信息异常！！");
        }
        return CommConstant.SUCCESS;
    }
}
