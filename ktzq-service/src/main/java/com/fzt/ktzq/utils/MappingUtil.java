package com.fzt.ktzq.utils;

import com.fzt.ktzq.dao.Dictionary;
import com.fzt.ktzq.redis.MappingCache;
import com.fzt.ktzq.service.DictionaryService;
import com.fzt.ktzq.util.StringUtilsFzt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 缓存映射工具类
 * @author 黄弋峰 2022/12/24
 */
public class MappingUtil {

    @Autowired
    static
    DictionaryService dictionaryService;

    private MappingUtil(){

    }

    private static final Logger logger = LoggerFactory.getLogger(MappingUtil.class);

    public static String getMappingValue(String domain, String key){
        String value = MappingCache.getValue(domain, key);
        if (StringUtilsFzt.isNotEmpty(value)){
            return value;
        } else {
            Dictionary dictionary = new Dictionary();
            dictionary.setDicName(key);
            dictionary.setMark(domain);
            List<Dictionary> list = dictionaryService.selectDictionary(dictionary);
            if (StringUtilsFzt.isNotEmpty(list) && list.size() == 1){
                Dictionary dictionary1 = list.get(0);
                MappingCache.setValue(dictionary1);
                return dictionary1.getDicTypeId();
            } else {
                logger.error("有问题，有大问题", domain, key);
            }
        }
        return null;
    }
}
