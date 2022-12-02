package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.College;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.service.CollegeService;
import com.fzt.ktzq.util.AuthUserContext;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.StringUtilsFzt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 球队服务
 * @author 黄弋峰 2022/12/2
 */
@RestController
public class CollegeController {
    private static final Logger logger = LoggerFactory.getLogger(CollegeController.class);

    @Autowired
    CollegeService collegeService;

    /**
     * 查询球队
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/selectCollege", method = RequestMethod.POST)
    public Map<String, List<College>> selectCollege() throws ServiceException{
        User user = AuthUserContext.getUser();
        logger.info("查询球队服务开始");
        List<College> colleges = new ArrayList<>();
        try {
            if (user.getCollegeNum() != null){
                logger.info("学院id，{}", user.getCollegeNum());
                colleges = collegeService.selectByCollegeNum(user.getCollegeNum());
            }
            if (user.getAereNum() != null){
                logger.info("区域id，{}", user.getAereNum());
                colleges = collegeService.selectByAereNum(user.getAereNum());
            }
        } catch (Exception e){
            logger.info("查询学院异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询学院异常");
        }
        Map<String, List<College>> map = new HashMap<>();
        map.put(CommConstant.DATA, colleges);
        return map;
    }

    @RequestMapping(value = "/addCollege", method = RequestMethod.POST)
    public RestResult<Object> addCollege(@RequestBody College college) throws ServiceException{
        logger.info("新增球队服务开始，请求参数，{}", college);
        Assert.notNull(college.getCollegeNum(), "球队编号不可为空");
        Assert.notNull(college.getCollegeName(), "球队名称不可为空");
        Assert.notNull(college.getAddressList(), "球队地址不可为空");
        Assert.notNull(college.getAddressxq(), "球队详细地址不可为空");
        Assert.notNull(college.getTel(), "区域电话不可为空");
        try {
            College name = new College();
            name.setCollegeName(college.getCollegeName());
            List<College> checkName = collegeService.selectCollege(name);
            if (StringUtilsFzt.isNotEmpty(checkName)){
                logger.info("球队名称重复，请重新输入");
                throw new ServiceException(CommConstant.ERROR_CODE, "球队名称重复，请重新输入");
            }
//            List<Integer>
        } catch (Exception e){

        }
    }
}
