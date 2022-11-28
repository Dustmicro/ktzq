package com.fzt.ktzq.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fzt.ktzq.config.FastBootConfig;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
}
