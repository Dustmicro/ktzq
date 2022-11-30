package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.Dictionary;
import com.fzt.ktzq.mapper.DictionaryMapper;
import com.fzt.ktzq.redis.MappingCache;
import com.fzt.ktzq.util.StringUtilsFzt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 统一服务
 * @author 黄弋峰 2022/11/29
 */
public class CommService {
    private static final Logger logger = LoggerFactory.getLogger(CommService.class);

    @Autowired
    DictionaryMapper dictionaryMapper;

    /**
     * 缓存中拿数据，如果没有就从数据库中查询
     * @param domain
     * @param key
     * @return
     */
    public String getDictionaryValue(String domain, String key) {
        String value = MappingCache.getValue(domain, key);
        if (StringUtilsFzt.isNotEmpty(value)){
            return value;
        } else {
            Dictionary db = new Dictionary();
            db.setDicTypeId(domain);
            db.setDicTypeId(key);
            List<Dictionary> list = dictionaryMapper.selectByExample(db);
            if (StringUtilsFzt.isNotEmpty(list) && list.size() == 1){
                Dictionary dictionary = list.get(0);
                MappingCache.setValue(dictionary);
                return dictionary.getDicName();
            } else {
                logger.error("domain{},key{}的值在字典至表中未找到！",domain, key);
            }
        }
        return null;
    }
}
