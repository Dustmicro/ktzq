package com.fzt.ktzq.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fzt.ktzq.config.FastBootConfig;
import com.fzt.ktzq.dao.OperationLog;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.service.OperationLogService;
import com.fzt.ktzq.service.UserService;
import com.fzt.ktzq.util.AuthUserContext;
import com.fzt.ktzq.util.StringUtilsFzt;
import com.fzt.ktzq.utils.TokenUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截器
 * @author 黄弋峰  2022/12/4
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    private static final String SERVICE_ID = "serviceId";
    private static final String COLLAGE_ID = "collageId";
    private static final String TOKEN = "token";
    private static final String SESSION_ID = "sessionId";

    @Autowired
    UserService userService;

    @Autowired
    FastBootConfig config;

    @Autowired
    OperationLogService operationLogService;

    @Autowired
    public SqlSession sqlSession;

    /**
     * 1、调用控制器方法前，进行拦截
     * 返回值为false代表拦截，true代表放行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        response.addHeader("X-Frame-Options", "SAMEORIGIN");//只能在本站页面嵌入
        response.addHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "*");
        logger.info("---------------------开始进入请求地址拦截---------------------");

        //从请求头中获取token
        String token = request.getHeader(TOKEN);
        if (token == null){
            logger.info("请求头token字段缺失");
            writeMsg(response, RestResult.failure("-3", "请求字段token缺失！！"));
            return false;
        }
        String userId = JWT.decode(token).getAudience().get(0);

        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userId)).build();
        String appId = null;
        try {
            DecodedJWT verify = jwtVerifier.verify(token);
            appId = verify.getClaim(TokenUtil.OWNER_FLAG).asString();
            logger.debug("appId:{}", appId);
        } catch (JWTVerificationException e){
            writeMsg(response, RestResult.failure("-3", "token验证未通过！！"));
            return false;
        }

        //判断token
        boolean jugeToken = jugeToken(token, response);
        if (!jugeToken){
            return false;
        }

        User user = null;
        user = userService.findUserById(Long.parseLong(userId));
        boolean needRet = checkTokenUser(user, response);
        if (!needRet){
            return false;
        }
        //刷新token
        response.setHeader(TOKEN, TokenUtil.getToken(user));

        return checkAll(response,request,user,userId);
    }

    /**
     * 判断token
     * @param token
     * @param response
     * @return
     */
    private boolean jugeToken(String token, HttpServletResponse response){
        try {
            JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j){
            writeMsg(response, RestResult.failure("-3", "token验证异常，请重新登陆！！"));
            return false;
        }
        return true;
    }

    /**
     *
     * @param user
     * @param response
     * @return
     */
    private boolean checkTokenUser(User user, HttpServletResponse response){
        if (StringUtilsFzt.isNull(user)){
            writeMsg(response, RestResult.failure("-1", "用户不存在"));
            return false;
        }
        return true;
    }

    private boolean checkAll(HttpServletResponse response, HttpServletRequest request, User user, String userId){
        /**在这里可以再添加验证球队是否越权、接口访问是否越权、服务访问是否越权等**/
        Map<String, Object> retMap = gitServiceId(request);
        Integer collegeId = (Integer) retMap.get(COLLAGE_ID);
        String serviceId = (String) retMap.get(SERVICE_ID);
        //将user设置到AuthUserContext中
        setUserToHeader(user,collegeId,serviceId);
        return true;
    }

    /**
     * 将请求中的user设置到AuthUserContext中
     * @param user
     * @param collegeId
     * @param serviceId
     */
    private void setUserToHeader(User user, Integer collegeId, String serviceId){
        Integer logId = handlerPre(user,serviceId,collegeId);
        user.setLogId(logId);
        AuthUserContext.setUser(user);
    }

    private Integer handlerPre(User user, String serviceId, Integer collegeId){
        String userName = user.getUserName();
        Long userId = user.getUserId();
        OperationLog operationLog = new OperationLog();
        operationLog.setOprateTime(new Date());
        operationLog.setUserId(userId);
        operationLog.setUserName(userName);
        operationLog.setCollegeId(collegeId == null? user.getCollegeId():collegeId);
        operationLogService.addOperationLog(operationLog);
        return operationLog.getOperationId();
    }

    /**
     * 从请求中获取collegeId和serviceId
     * @param request
     * @return
     */
    private Map<String, Object> gitServiceId(HttpServletRequest request){
        /**在这里还可以再添加请求路径，针对文件上传**/
        return getByAttribute(request);
    }

    /**
     * 从属性中获取值
     * @param request
     * @return
     */
    private Map<String, Object> getByAttribute(HttpServletRequest request){
        Integer collegeId = (Integer) request.getAttribute(COLLAGE_ID);
        String serviceId = (String) request.getAttribute(SERVICE_ID);
        Map<String, Object> retMap = new HashMap<>(3);
        retMap.put(COLLAGE_ID, collegeId);
        retMap.put(SERVICE_ID, serviceId);
        return retMap;
    }

    private void writeMsg(HttpServletResponse response, RestResult<Object> restResult){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset = utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            logger.error("发生异常！！");
        }
        if (writer == null){
            return;
        }
        writer.write(JSON.toJSONString(restResult));
    }

    /**
     * 2、在调用控制器方法后，拦截（生成视图之前）
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        logger.info("---------------------进入拦截器了，请求处理后，渲染ModelAndView前调用---------------------");
        //删除用户
        AuthUserContext.remove();
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 3、生成视图之后(后台所有逻辑都完成后)
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        logger.info("---------------------进入拦截器了，渲染ModelAndView后调用---------------------");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
