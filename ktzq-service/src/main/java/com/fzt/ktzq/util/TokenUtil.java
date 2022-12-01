package com.fzt.ktzq.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.redis.MappingCache;

import java.util.Date;

public class TokenUtil {
    private TokenUtil(){

    }
    public static final String OWNER_FLAG = "ownerFlag";
    //设置过期时间
    private static Long expireTime = null;

    public static String getToken(User user){
        setExpireTime();
        String token = "";
        Date date = new Date();
        date.setTime(System.currentTimeMillis() + expireTime);
        token = JWT.create().withAudience(String.valueOf(user.getUserId()))
                .withClaim(OWNER_FLAG, "0")
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(String.valueOf(user.getUserId())));
        return token;
    }

    /**
     * 设置expireTime
     */
    public static void setExpireTime(){
        if (expireTime == null){
            String value = MappingCache.getValue(MappingCache.COMMON_DOMAIN, "tokenExpTime");
            if (value != null){
                int parseInt = Integer.parseInt(value)*60;
                expireTime = Long.valueOf(parseInt*1000L);
            }
        }
    }

    /**
     * 获取token
     * 争对小程序用户
     * @param user
     * @return
     */
//    public static String getToken(User user){
//        return CommConstant.SUCCESS;
//    }

}
