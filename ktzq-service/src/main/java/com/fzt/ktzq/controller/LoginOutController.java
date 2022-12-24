package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.redis.JWTCache;
import com.fzt.ktzq.redis.MappingCache;
import com.fzt.ktzq.util.AuthUserContext;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.StringUtilsFzt;
import com.fzt.ktzq.utils.MappingUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 退出登录控制类
 * @author 黄弋峰 2022/12/24
 */
public class LoginOutController {
    private static final Logger logger = LoggerFactory.getLogger(LoginOutController.class);

    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public String loginOut() throws ServiceException {
        logger.info("退出操作");
        try {
            User user = AuthUserContext.getUser();
            String token = JWTCache.getValue(JWTCache.JWT_PREFIX + user.getUserId());
            if (StringUtilsFzt.isEmpty(token)){
                JWTCache.removeValue(JWTCache.JWT_PREFIX + user.getUserId());
            }
            String tokenExpTime = MappingUtil.getMappingValue(MappingCache.COMMON_DOMAIN, "tokenExpTime");
            int tokenExpTimes = 15*60;
            if (tokenExpTime != null){
                tokenExpTimes = Integer.parseInt(tokenExpTime) * 60;
            }
            JWTCache.setValue(JWTCache.LOGOUT_PREFIX+user.getUserId(), "-1", tokenExpTimes);
            //退出是删除sessionId
            JWTCache.removeValue(JWTCache.SESSION_PREFIX+user.getUserId());
            return CommConstant.SUCCESS;
        } catch (Exception e){
            logger.info("退出异常");
            throw  new ServiceException(CommConstant.ERROR_CODE, "退出异常");
        }
    }
}
