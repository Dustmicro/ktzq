package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.Dictionary;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.service.DictionaryService;
import com.fzt.ktzq.util.CommConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典值服务
 * @author 黄弋峰  2022/11/27
 */
@RestController
public class DictionaryController {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 查询所有字典值数据
     * @return
     */
    @GetMapping("/selectDictionaryAll")
    public List<Dictionary> selectAll(){
        List<Dictionary> list = dictionaryService.selectAll();
        return list;
    }

    /**
     * 分页查询字典值
     * @param dictionary
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/selectDictionary", method = RequestMethod.POST)
    public List<Dictionary> selectDictionary(@RequestBody Dictionary dictionary) throws ServiceException{
        logger.info("分页查询字典值服务开始，请求参数，{}", dictionary);
        try {
            List<Dictionary> list = dictionaryService.selectDictionary(dictionary);
            return list;
        } catch (Exception e){
            logger.info("查询字典值异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询字典值异常");
        }

    }

    /**
     * 新增字典值
     * @param dictionary
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/addDictionary", method = RequestMethod.POST)
    public RestResult<Object> addDictionary(@RequestBody Dictionary dictionary) throws ServiceException {
        logger.info("新增字典值服务开始，请求参数，{}", dictionary);
        Assert.notNull(dictionary.getDicName(), "字典值名称不可为空");
        Assert.notNull(dictionary.getDicTypeId(), "字典值类型不可为空");
        try{
            dictionaryService.addDictionary(dictionary);
        } catch (Exception e){
            logger.info("新增字典值异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增字典值异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 删除字典值
     * @param dictionary
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/deleteDictionary", method = RequestMethod.POST)
    public RestResult<Object> deleteDictionary(@RequestBody Dictionary dictionary) throws ServiceException{
        logger.info("删除字典值服务开始，请求参数，{}", dictionary);
        try {
            dictionaryService.deleteDictionary(dictionary);
        } catch (Exception e){
            logger.info("删除字典值异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "删除字典值异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 修改字典值
     * @param dictionary
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/updateDictionary", method = RequestMethod.POST)
    public RestResult<Object> updateDictionary(@RequestBody Dictionary dictionary) throws ServiceException{
        logger.info("修改字典值服务开始，请求参数，{}", dictionary);
        try {
            dictionaryService.updateDictionary(dictionary);
        } catch (Exception e){
            logger.info("修改字典值异常！！");
            throw new ServiceException(CommConstant.ERROR_CODE, "修改字典值异常！！");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }
}