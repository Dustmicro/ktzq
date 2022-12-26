package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.Aere;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.service.AereService;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.StringUtilsFzt;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域服务控制类
 * @author 黄弋峰  2022/12/3
 */
@ApiModel(description = "区域服务控制类")
@RestController
public class AereController {
    private static final Logger logger = LoggerFactory.getLogger(AereController.class);

    @Autowired
    AereService aereService;

    /**
     * 查询所有区域数据
     * @return
     */
    @ApiOperation(value = "查询所有区域数据")
    @GetMapping("/selectAereAll")
    public List<Aere> selectAereAll(){
        return aereService.selectAereAll();
    }

    /**
     * 根据条件查询区域
     * @param aere
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "根据条件查询区域")
    @RequestMapping(value = "/selectAere", method = RequestMethod.POST)
    public List<Aere> selectAere(@RequestBody Aere aere) throws ServiceException{
        logger.info("查询区域服务开始，请求参数，{}", aere);
        List<Aere> list = new ArrayList<>();
        try {
            list = aereService.selectAere(aere);
        } catch (Exception e){
            logger.info("查询区域异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询区域异常");
        }
        return list;
    }

    /**
     * 新增球队
     * @param aere
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "新增区域")
    @RequestMapping(value = "/addAere", method = RequestMethod.POST)
    public RestResult<Object> addAere(@RequestBody Aere aere) throws ServiceException{
        logger.info("新增区域服务开始，请求参数，{}", aere);
        Assert.notNull(aere.getAereNum(), "区域编号不能为空");
        Assert.notNull(aere.getAereName(), "区域名称不能为空");
        Assert.notNull(aere.getUserId(), "负责人id不能为空");
        Assert.notNull(aere.getUserName(), "负责人名称不能为空");
        Assert.notNull(aere.getTel(), "电话不能为空");
        Assert.notNull(aere.getAddress(), "区域地址不能为空");
        try {
            Aere name = new Aere();
            name.setAereName(aere.getAereName());
            List<Aere> list = aereService.selectAere(name);
            if (StringUtilsFzt.isNotEmpty(list)){
                throw new ServiceException(CommConstant.ERROR_CODE, "球队名称为" + aere.getAereName() + "的球队已存在，请重新输入");
            }
            name.setAereNum(null);
            name.setAereNum(aere.getAereNum());
            List<Aere> num = aereService.selectAere(name);
            if (StringUtilsFzt.isNotEmpty(num)){
                throw new ServiceException(CommConstant.ERROR_CODE, "球队编号为" + aere.getAereNum() + "的球队已存在，请重新输入");
            }
            aereService.addAere(aere);
        } catch (ServiceException e){
            logger.info("新增区域异常");
            throw new ServiceException(CommConstant.ERROR_CODE, e.getDesc());
        } catch (Exception e){
            logger.info("新增区域异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增区域异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 修改区域
     * @param aere
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "修改区域")
    @RequestMapping(value = "/updateAere", method = RequestMethod.POST)
    public RestResult<Object> updateAere(@RequestBody Aere aere) throws ServiceException{
        logger.info("修改区域服务开始，请求参数，{}", aere);
        try {
            //修改之前这里还应该做一些校验
            //修改的同时还应该修改一些相关联的表
            aereService.updateAere(aere);
        } catch (Exception e){
            logger.info("修改区域异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "修改区域异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 删除区域
     * @param aere
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "删除区域")
    @RequestMapping(value = "/deleteAere", method = RequestMethod.POST)
    public RestResult<Object> deleteAere(@RequestBody Aere aere) throws ServiceException{
        logger.info("删除区域服务开始，请求参数，{}", aere);
        try {
            //删除之前这里还应该做一些校验
            aereService.updateAere(aere);
        } catch (Exception e){
            logger.info("修改区域异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "修改区域异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }
}
