package com.fzt.ktzq.service;

import com.fzt.ktzq.appmid.common.parser.ServiceException;
import com.fzt.ktzq.dao.Dictionary;
import com.fzt.ktzq.mapper.DictionaryMapper;
import com.fzt.ktzq.util.CommConstant;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 字典值服务
 * @author 黄弋峰 2022/11/27
 */
@Service
public class DictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    @Autowired
    private DictionaryMapper dictionaryMapper;

    public String addDictionary(Dictionary dictionary, SqlSession sqlSession) throws ServiceException {
        logger.info("新增部门服务开始，请求参数，{}", dictionary);
        Assert.notNull(dictionary.getDicName(), "字典值名称不可为空");
        Assert.notNull(dictionary.getDicTypeId(), "字典值类型不可为空");
        try{
            sqlSession.getMapper(DictionaryMapper.class).insert(dictionary);
        } catch (Exception e){
            logger.info("新增字典值异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增字典值异常");
        }
        return CommConstant.SUCCESS;
    }
}
