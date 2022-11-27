package com.fzt.ktzq.controller;

import com.fzt.ktzq.appmid.common.parser.ServiceException;
import com.fzt.ktzq.dao.Dictionary;
import com.fzt.ktzq.service.DictionaryService;
import com.fzt.ktzq.util.CommConstant;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
     * 查询所有数据
     * @return
     */
    @GetMapping("/selectDictionaryAll")
    public List<Dictionary> selectAll(){
        List<Dictionary> list = dictionaryService.selectAll();
        return list;
    }

    @RequestMapping(value = "/addDictionary", method = RequestMethod.POST)
    public String addDictionary(@RequestBody Dictionary dictionary) throws ServiceException {
        logger.info("新增部门服务开始，请求参数，{}", dictionary);
        Assert.notNull(dictionary.getDicName(), "字典值名称不可为空");
        Assert.notNull(dictionary.getDicTypeId(), "字典值类型不可为空");
        try{
            dictionaryService.addDictionary(dictionary);
        } catch (Exception e){
            logger.info("新增字典值异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增字典值异常");
        }
        return CommConstant.SUCCESS;
    }
}
