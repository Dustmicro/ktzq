package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.CollegeMember;
import com.fzt.ktzq.service.CollegeMemberService;
import com.fzt.ktzq.util.CommConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 球队成员控制类
 * @author 黄弋峰 2022/12/13
 */
@RestController
public class CollegeMemberController {
    private static final Logger logger = LoggerFactory.getLogger(CollegeMemberController.class);

    @Autowired
    CollegeMemberService collegeMemberService;

    /**
     * 查询球队成员类型
     * @return
     */
    @GetMapping("/selectMemberType")
    public List<CollegeMember> selectMemberType() throws ServiceException{
        List<CollegeMember> list = new ArrayList<>();
        try {
            list = collegeMemberService.selectMemberType();
        } catch (Exception e){
            throw new ServiceException(CommConstant.ERROR_CODE, "查询球队成员类型异常");
        }
        return list;
    }

    /**
     * 查询球队成员信息
     * @param collegeMember
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/selectMember", method = RequestMethod.POST)
    public List<CollegeMember> selectMember(@RequestBody CollegeMember collegeMember) throws ServiceException{
        logger.info("查询球队成员服务开始，请求参数，{}", collegeMember);
        Assert.notNull(collegeMember.getCollegeId(), CommConstant.COLLEGE_ID_NOT_NULL);
        List<CollegeMember> list = new ArrayList<>();
        try {
            list = collegeMemberService.selectMember(collegeMember);
        } catch (Exception e){
            throw new ServiceException(CommConstant.ERROR_CODE, "查询球队成员信息异常");
        }
        return list;
    }

    /**
     * 新增球队成员
     * @param collegeMember
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public String addMember(@RequestBody CollegeMember collegeMember) throws ServiceException{
        logger.info("新增球队成员服务开始，请求参数，{}", collegeMember);
        Assert.notNull(collegeMember.getMemberTypeCd(), "球队成员类型不可为空！！");
        Assert.notNull(collegeMember.getName(), "姓名不可为空");
        try {
            collegeMemberService.addMember(collegeMember);
            //这里还应该在添加一些数据校验
        } catch (Exception e){
            throw new ServiceException(CommConstant.ERROR_CODE, "新增球队成员信息异常");
        }
        return CommConstant.SUCCESS;
    }
}