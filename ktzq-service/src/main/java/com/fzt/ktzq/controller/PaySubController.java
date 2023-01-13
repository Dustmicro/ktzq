package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.PaySub;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.service.PaySubService;
import com.fzt.ktzq.util.CommConstant;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收费科目控制类
 * @author 黄弋峰 2023/1/13
 */
@RestController
public class PaySubController {
    private static final Logger logger = LoggerFactory.getLogger(PaySubController.class);

    @Autowired
    PaySubService paySubService;

    /**
     * 查询全部收费科目
     * @return
     */
    @ApiOperation(value = "查询全部收费科目")
    @GetMapping("/selectPaySubAll")
    public List<PaySub> selectPaySubAll(){
        return paySubService.selectPaySubAll();
    }

    /**
     * 新增收费科目
     * @param paySub
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "新增收费科目")
    @RequestMapping(value = "/addPaySub", method = RequestMethod.POST)
    public RestResult<String> addPaySub(@RequestBody PaySub paySub) throws ServiceException{
        logger.info("新增收费科目服务开始，请求参数，{}", paySub);
        try {
            paySubService.addPaySub(paySub);
        } catch (Exception e){
            logger.info("新增异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 修改收费科目
     * @param paySub
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "修改收费科目")
    @RequestMapping(value = "/updatePaySub", method = RequestMethod.POST)
    public RestResult<String> updatePaySub(@RequestBody PaySub paySub) throws ServiceException{
        logger.info("修改收费科目服务开始，请求参数，{}", paySub);
        try {
            //在这里还应该加入一些校验，该科目下是否还有未结清的费用
            paySubService.updatePaySub(paySub);
        } catch (Exception e){
            logger.info("修改异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "修改异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 删除收费科目
     * @param paySub
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "删除收费科目")
    @RequestMapping(value = "/deletePaySub", method = RequestMethod.POST)
    public RestResult<String> deletePaySub(@RequestBody PaySub paySub) throws ServiceException{
        logger.info("删除收费科目服务开始，请求参数，{}", paySub);
        try {
            //在这里还应该加入一些校验，该科目下是否还有未结清的费用
            paySubService.deletePaySub(paySub);
        } catch (Exception e){
            logger.info("删除异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "删除异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }
}
