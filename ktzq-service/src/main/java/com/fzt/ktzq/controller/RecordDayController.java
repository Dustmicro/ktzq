package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.RecordDay;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.service.RecordDayService;
import com.fzt.ktzq.util.CommConstant;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 每日考勤控制类
 * @author 黄弋峰 2023/1/18
 */
@RestController
public class RecordDayController {
    private static final Logger logger = LoggerFactory.getLogger(RecordDayController.class);

    @Autowired
    RecordDayService recordDayService;

    /**
     * 查询每日考勤（根据条件查询）
     * @param recordDay
     * @return
     * @throws ServiceException
     */
    @ApiOperation("查询每日考勤")
    @RequestMapping(value = "/selectRecordDay",method = RequestMethod.GET)
    public Map<String, List<RecordDay>> selectRecordDay(@RequestBody RecordDay recordDay) throws ServiceException{
        logger.info("查询考勤服务开始，请求参数，{}", recordDay);
        try {
            /**
             * 后期优化：1、人员考勤状态根据时间戳来判断
             *         2、如何做到管理者在录入数据的时候能够实时
             *         3、考勤记录方式：1、录入在职的人员，通过update状态来实现考勤状态
             *                       2、正常insert插入
             */
            List<RecordDay> list = recordDayService.selectRecordDay(recordDay);
            Map<String, List<RecordDay>> map = new HashMap<>();
            map.put("list", list);
            return map;
        } catch (Exception e){
            logger.info("查询异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询异常");
        }
    }

    /**
     * 查询所有考勤数据（直接查询1）
     * @return
     * @throws ServiceException
     */
    @ApiOperation("查询所有考勤数据")
    @GetMapping(value = "/selectRecordDayAll")
    public Map<String, List<RecordDay>> selectRecordDayAll() throws ServiceException{
        logger.info("查询考勤服务开始");
        try {
            List<RecordDay> list = recordDayService.selectRecordDayAll();
            Map<String, List<RecordDay>> map = new HashMap<>();
            map.put("list", list);
            return map;
        } catch (Exception e){
            logger.info("查询异常！");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询异常");
        }
    }

    /**
     * 新增考勤
     * @param recordDay
     * @return
     * @throws ServiceException
     */
    @ApiOperation("新增考勤")
    @RequestMapping(value = "/addRecordDay",method = RequestMethod.POST)
    public RestResult<String> addRecordDay(@RequestBody RecordDay recordDay) throws ServiceException{
        logger.info("新增考勤服务开始，请求参数，{}", recordDay);
        Assert.isNull(recordDay.getCreateTime(), "考勤时间不能为空");
        Assert.isNull(recordDay.getCollegeMemberName(), "球队成员姓名不可为空");
        try {
            /**
             * 后期强制优化：1、如何同步数据
             *         2、能否单条数据添加，或是指定批量导入
             *         3、时间是手动添加还是实时时间
             */
            recordDayService.addRecordDay(recordDay);
        } catch (Exception e){
            logger.info("新增异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 删除每日考勤
     * @param recordDay
     * @return
     * @throws ServiceException
     */
    @ApiOperation("删除每日考勤")
    @RequestMapping(value = "/deleteRecordDay",method = RequestMethod.POST)
    public RestResult<String> deleteRecordDay(@RequestBody RecordDay recordDay) throws ServiceException{
        logger.info("删除考勤服务开始，请求参数，{}", recordDay);
        try {
            recordDayService.deleteRecordDay(recordDay);
        } catch (Exception e){
            logger.info("删除异常！");
            throw new ServiceException(CommConstant.ERROR_CODE, "删除异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 修改每日考勤
     * @param recordDay
     * @return
     * @throws ServiceException
     */
    @ApiOperation("修改每日考勤")
    @RequestMapping(value = "/updateRecordDay",method = RequestMethod.POST)
    public RestResult<String> updateRecordDay(@RequestBody RecordDay recordDay) throws ServiceException{
        logger.info("修改考勤服务开始，请求参数，{}", recordDay);
        try {
            /**
             * 优化服务要求及注意事项同上面一样1
             */
            recordDayService.updateRecordDay(recordDay);
        } catch (Exception e){
            logger.info("修改异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "修改异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }
}
