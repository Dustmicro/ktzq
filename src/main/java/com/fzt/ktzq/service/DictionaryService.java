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

import java.util.List;

/**
 * 字典值服务
 * @author 黄弋峰 2022/11/27
 */
@Service
public class DictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    @Autowired
    private DictionaryMapper dictionaryMapper;

    /**
     * 新增字典值
     * @param dictionary
     * @return
     * @throws ServiceException
     */
    public String addDictionary(Dictionary dictionary) throws ServiceException {
        try{
            dictionaryMapper.insertSelective(dictionary);
        } catch (Exception e){
            logger.info("新增字典值异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增字典值异常");
        }
        return CommConstant.SUCCESS;
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<Dictionary> selectAll(){
        return dictionaryMapper.selectAll();
    }
}
