package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.Dept;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.service.DeptService;
import com.fzt.ktzq.util.CommConstant;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门服务类
 * @author 黄弋峰  2022/12/1
 */
@RestController
public class DeptController {
    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
    private static final String DEPT_NAME_NOT_NULL = "部门名称不可为空";
    private static final String DEPT_ID_NOT_NULL = "部门编号不可为空";

    @Autowired
    DeptService deptService;

    /**
     * 查询所有部门数据
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "查询所有部门数据")
    @GetMapping("/selectDeptAll")
    public List<Dept> selectDeptAll() throws ServiceException{
        List<Dept> list = deptService.selectDeptAll();
        return list;
    }

    /**
     * 根据条件查询部门
     * @param dept
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "根据条件查询部门")
    @RequestMapping(value = "/selectDept", method = RequestMethod.POST)
    public List<Dept> selectDept(@RequestBody Dept dept) throws ServiceException{
        logger.info("查询部门服务开始，请求参数，{}", dept);
        List<Dept> list = new ArrayList<>();
        try {
            list = deptService.selectDept(dept);
        } catch (Exception e){
            logger.info("查询部门异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询部门异常");
        }
        return list;
    }

    /**
     * 新增学院下的部门
     * @param dept
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "新增球队下的部门")
    @RequestMapping(value = "/addDeptByCollege", method = RequestMethod.POST)
    public RestResult<Object> addDeptByCollege(@RequestBody Dept dept) throws ServiceException{
        logger.info("新增学院下部门服务开始，请求参数，{}", dept);
        Assert.notNull(dept.getDeptName(), "学院下部门名称不可为空");
        Assert.notNull(dept.getDeptId(), DEPT_ID_NOT_NULL);
        try {
            deptService.addDeptByCollege(dept);
        } catch (Exception e){
            logger.info("新增部门异常");
            throw  new ServiceException(CommConstant.ERROR_CODE, "新增部门异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 新增区域下部门
     * @param dept
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "新增区域下部门")
    @RequestMapping(value = "/addDeptByAere", method = RequestMethod.POST)
    public RestResult<Object> addDeptByAere(@RequestBody Dept dept) throws ServiceException{
        logger.info("新增区域下部门服务开始，请求参数，{}", dept);
        Assert.notNull(dept.getDeptName(), "区域下部门名称不可为空");
        Assert.notNull(dept.getDeptId(), DEPT_ID_NOT_NULL);
        try {
            deptService.addDeptByAere(dept);
        } catch (Exception e){
            logger.info("新增部门异常");
            throw  new ServiceException(CommConstant.ERROR_CODE, "新增部门异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 修改部门服务
     * @param dept
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "修改部门服务")
    @RequestMapping(value = "/updateDept", method = RequestMethod.POST)
    public RestResult<Object> updateDept(@RequestBody Dept dept) throws ServiceException{
        logger.info("修改部门服务开始，请求参数，{}", dept);
        try {
            deptService.updateDept(dept);
        } catch (Exception e){
            logger.info("修改部门异常");
            throw  new ServiceException(CommConstant.ERROR_CODE, "修改部门异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 删除部门服务
     * @param dept
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "删除部门服务")
    @RequestMapping(value = "/deleteDept", method = RequestMethod.POST)
    public RestResult<Object> deleteDept(@RequestBody Dept dept) throws ServiceException{
        logger.info("删除部门服务开始，请求参数，{}", dept);
        try {
            deptService.deleteDept(dept);
        } catch (Exception e){
            logger.info("删除部门异常");
            throw  new ServiceException(CommConstant.ERROR_CODE, "删除部门异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }
}
