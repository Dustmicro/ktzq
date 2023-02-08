package com.fzt.ktzq.controller;

import com.alibaba.fastjson.JSON;
import com.fzt.ktzq.common.appmid.bean.ServiceParamsBean;
import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.ResponseVo;
import com.fzt.ktzq.dao.RspPage;
import com.fzt.ktzq.dao.Task;
import com.fzt.ktzq.service.TaskService;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.HttpUtil;
import com.fzt.ktzq.util.StringUtilsFzt;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 定时任务控制类
 * @author 黄弋峰 2023/2/8
 */
@RestController
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskService taskService;

    /**
     * 查询所有定时任务
     * @return
     */
    @ApiOperation("查询所有定时任务")
    @GetMapping("/selectTaskAll")
    public List<Task> selectTaskAll() {
        return taskService.selectTaskAll();
    }

    /**
     * 查询定时任务
     * @param task
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/selectTask", method = RequestMethod.POST)
    public RspPage<Task> selectTask(@RequestBody Task task) throws ServiceException{
        logger.info("查询定时任务服务开始，请求参数，{}", task);
        try {
            List<Task> list = taskService.selectTask(task);
            Page<Task> page = (Page<Task>) list;
            return RspPage.getRspPage(page);
        } catch (Exception e) {
            logger.info("查询异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询异常");
        }
    }

    /**
     * 新增定时任务
     * @param task
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public String addTask(@RequestBody Task task) throws ServiceException{
        logger.info("新增定时任务服务开始，请求参数，{}", task);
        Assert.notNull(task.getClassBean(), "对应class不可为空");
        Assert.notNull(task.getTaskName(), "任务名称不可为空");
        Assert.notNull(task.getTaskCron(), "对应时间不可为空");
        try {
            taskService.addTask(task);
        } catch (Exception e) {
            logger.info("查询异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询异常");
        }
        return CommConstant.SUCCESS;
    }

    /**
     * 开启定时任务
     * @param task
     * @param params
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/startTask", method = RequestMethod.POST)
    public String startTask(@RequestBody Task task, ServiceParamsBean params) throws ServiceException{
        logger.info("开启定时任务服务开始，请求参数，{}", task);
        Assert.notNull(task.getTaskId(), "任务id不可为空");
        try {
            callService("startTaskUrl", params, task);
        } catch (Exception e) {
            logger.info("开启定时任务异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "开启定时任务异常");
        }
        return CommConstant.SUCCESS;
    }

    /**
     * 调用服务
     * @param service
     * @param params
     * @param task
     * @throws ServiceException
     */
    private void callService(String service, ServiceParamsBean params, Task task) throws ServiceException {
        Map<String, String> paramsMap = params.getParamsMap();
        String startTaskUrl = paramsMap.get(service);
        Assert.notNull(startTaskUrl, "配置定时任务的请求路径为空！！");
        String rsp = HttpUtil.sendPost(startTaskUrl, JSON.toJSONString(task));
        if (StringUtilsFzt.isNotEmpty(rsp)) {
            ResponseVo responseVo = JSON.parseObject(rsp, ResponseVo.class);
            String code = responseVo.getCode();
            if (!"1".equals(code)) {
                throw new ServiceException(CommConstant.ERROR_CODE, responseVo.getMsg());
            }

        } else {
            throw new ServiceException(CommConstant.ERROR_CODE, "调用定时任务服务返回为空");
        }
    }
}
