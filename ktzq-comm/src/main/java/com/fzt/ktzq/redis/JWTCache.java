package com.fzt.ktzq.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by wuxw on 2018/5/5.
 */
public class JWTCache extends BaseCache{

    public static final String LOGOUT_PREFIX = "logout_prefix";
    public static final String JWT_PREFIX = "jwt_prefix_";
    public static final String SESSION_PREFIX = "session_";
    public static final int SESSION_EXPIRE_TIME = 901;

    /**
     * 获取值(用户ID)
     * @returne
     */
    public static String getValue(String jdi){
        Jedis redis = null;
        try {
            redis = getJedis();
            return redis.get(jdi);
        }finally {
            if(redis != null){
                redis.close();
            }
        }
    }

    /**
     * 保存数据
     * @param jdi
     */
    public static void setValue(String jdi,String userId,int expireTime){
        Jedis redis = null;
        try {
            redis = getJedis();
            redis.set(jdi,userId);
            redis.expire(jdi,expireTime);
        }finally {
            if(redis != null){
                redis.close();
            }
        }

    }

    /**
     * 删除记录
     * @param jdi
     */
    public static void removeValue(String jdi){
        Jedis redis = null;
        try {
            redis = getJedis();
            redis.del(jdi);
        }finally {
            if(redis != null){
                redis.close();
            }
        }
    }

    /**
     * 重设超时间
     * @param jdi
     * @param expireTime
     */
    public static void resetExpireTime(String jdi,int expireTime){

        Jedis redis = null;
        try {
            redis = getJedis();
            redis.expire(jdi,expireTime);
        }finally {
            if(redis != null){
                redis.close();
            }
        }
    }
}
