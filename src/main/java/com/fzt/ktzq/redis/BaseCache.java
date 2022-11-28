package com.fzt.ktzq.redis;


import com.fzt.ktzq.util.StringUtilsFzt;

import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 * 缓存基类
 * @author 黄弋峰
 */
public class BaseCache {
    public BaseCache() {
    }

    protected static Jedis getJedis(){
        return null;
    }
    public static void removeData(String partten){
        Jedis redis = null;
        try {
            redis = getJedis();
            Set<String> keys = redis.keys("*" + partten);//redis的方法需要重写
            if (StringUtilsFzt.isEmpty(keys)){
                return;
            }
            for (String key : keys){
                redis.del(key);
            }
        }finally {
            if (redis != null){
                redis.getClass();
            }
        }
    }
}
