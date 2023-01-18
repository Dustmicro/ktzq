package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.RecordDay;
import com.fzt.ktzq.service.RecordDayService;
import com.fzt.ktzq.util.CommConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 查询每日考勤
     * @param recordDay
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/selectRecordDay",method = RequestMethod.GET)
    public List<RecordDay> selectRecordDay(@RequestBody RecordDay recordDay) throws ServiceException{
        logger.info("查询考勤服务开始，请求参数，{}", recordDay);
        try {
            /**
             * 后期优化：1、人员考勤状态根据时间戳来判断
             *         2、如何做到管理者在录入数据的时候能够实时
             *         3、考勤记录方式：1、录入在职的人员，通过update状态来实现考勤状态
             *                       2、正常insert
             */
            List<RecordDay> list = recordDayService.selectRecordDay(recordDay);
            return list;
        } catch (Exception e){
            logger.info("查询异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询异常");
        }
    }
}
