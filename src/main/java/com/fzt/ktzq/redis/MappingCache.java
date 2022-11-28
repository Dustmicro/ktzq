package com.fzt.ktzq.redis;

import com.fzt.ktzq.constant.DomainContant;
import com.fzt.ktzq.dao.Dictionary;
import com.fzt.ktzq.util.DateUtil;
import com.fzt.ktzq.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * 映射缓存工具类
 * @author 黄弋峰 2022/11/12
 */
public class MappingCache extends BaseCache {
    private static final Logger logger = LoggerFactory.getLogger(MappingCache.class);

    //后缀 用来刷缓存时删除 所有以这个为后缀的数据
    public final static String _SUFFIX_MAPPING = "_SUFFIX_MAPPING";
    //日志
    public final static String LOG_SERVICE_CODE = "LOG_SERVICE_CODE";

    /**公共域**/
    public static final String COMMON_DOMAIN = "DOMAIN.COMMON";

    /**
     * 获取值
     * @param domain
     * @param key
     * @return
     */
    public static String getValue(String domain, String key) {
        Jedis redis = null;
        long startTime = DateUtil.getCurrentDate().getTime();
        try {
            redis = getJedis();
            Object object = SerializeUtil.unserialize(redis.get((domain + key + _SUFFIX_MAPPING).getBytes()));
            if (object == null) {
                return null;
            }

            Dictionary mapping = (Dictionary) object;
            return mapping.getDicName();
        } finally {
            if (redis != null) {
                redis.close();
            }

            logger.debug(domain + "::" + key + " redis 耗时：" + (DateUtil.getCurrentDate().getTime() - startTime));
        }
    }

    /**
     * 获取公用域下的key值
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        Dictionary mapping = getMapping(key);
        return mapping == null ? "" : mapping.getDicName();
    }

    public static Dictionary getMapping(String key) {
        Jedis redis = null;
        long startTime = DateUtil.getCurrentDate().getTime();

        try {
            redis = getJedis();
            Object obj = SerializeUtil.unserialize(redis.get((DomainContant.COMMON_DOMAIN + key + _SUFFIX_MAPPING).getBytes()));
            if (obj instanceof Dictionary) {
                return (Dictionary) obj;
            }
        } finally {
            if (redis != null) {
                redis.close();
            }
            logger.debug( key + " redis 耗时：" + (DateUtil.getCurrentDate().getTime() - startTime));

        }
        return null;
    }
}
