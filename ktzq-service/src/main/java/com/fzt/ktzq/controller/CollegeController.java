package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.College;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.service.CollegeService;
import com.fzt.ktzq.util.AuthUserContext;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.StringUtilsFzt;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
    @ApiOperation(value = "查询球队")
    @RequestMapping(value = "/selectCollege", method = RequestMethod.POST)
    public Map<String, List<College>> selectCollege() throws ServiceException{
        User user = AuthUserContext.getUser();
        logger.info("查询球队服务开始");
        List<College> colleges = new ArrayList<>();
        try {
            if (user.getCollegeId() != null){
                logger.info("学院id，{}", user.getCollegeId());
                colleges = collegeService.selectByCollegeNum(user.getCollegeId());
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

    /**
     * 新增球队
     * @param college
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "新增球队")
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
            List<Integer> addressList = college.getAddressList();
            List<College> acctList = college.getAcctList();
            StringBuilder bd = new StringBuilder();
            if (addressList != null){
                for (Integer code : addressList){
                    bd.append(code).append(",");
                }
                bd.deleteCharAt(bd.length() - 1);
            }
            college.setAereNum(college.getAereNum());
            college.setAddress(bd.toString());
            college.setCreateTime(new Date());
            collegeService.addCollege(college);
        } catch (ServiceException e){
            logger.info("球队名称重复，请重新输入");
            throw new ServiceException(CommConstant.ERROR_CODE, e.getDesc());
        } catch (Exception e){
            logger.info("新增球队出现异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增球队出现异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 修改球队服务
     * @param college
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "修改球队")
    @RequestMapping(value = "/updateCollege", method = RequestMethod.POST)
    public RestResult<Object> updateCollege(@RequestBody College college) throws ServiceException{
        logger.info("修改部门服务开始，请求参数，{}", college);
        Assert.notNull(college.getCollegeName(), "球队名称不可为空");
        try {
            College college1 = collegeService.find(college.getCollegeId());
            if (college1 == null){
                throw new ServiceException(CommConstant.ERROR_CODE, "不存在该球队信息");
            }
            College param = new College();
            param.setCollegeName(college.getCollegeName());
            College checkName = collegeService.checkName(param);
            if (!college1.getCollegeName().equals(college.getCollegeName()) && checkName != null){
                throw new ServiceException(CommConstant.ERROR_CODE, "已有球队名称为" + param.getCollegeName() + "的小区");
            }
            if (college.getAddressList() != null){
                List<Integer> addressList = college.getAddressList();
                StringBuilder bd = new StringBuilder();
                if (addressList != null){
                    for (Integer code : addressList){
                        bd.append(code).append(",");
                    }
                    bd.deleteCharAt(bd.length() - 1);
                }
                college.setAddress(bd.toString());
            }
            collegeService.updateCollege(college);
        } catch (ServiceException e){
            logger.info("修改球队异常");
            throw new ServiceException(CommConstant.ERROR_CODE, e.getDesc());
        } catch (Exception e){
            logger.info("修改球队异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "修改球队异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 删除球队服务
     * @param college
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "删除球队")
    @RequestMapping(value = "/deleteCollege", method = RequestMethod.POST)
    public RestResult<Object> deleteCollege(@RequestBody College college) throws ServiceException{
        logger.info("修改球队服务开始，请求参数，{}", college);
        try {
            collegeService.deleteCollege(college);
        } catch (Exception e){
            logger.info("删除球队异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "删除球队异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }
}
