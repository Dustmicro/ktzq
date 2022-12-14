package com.fzt.ktzq.config;

import com.fzt.ktzq.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 * @author 黄弋峰  2022/12/4
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){

        //定义排除swagger访问的路径配置
        String[] swaggerExcludes=new String[]{"/swagger-ui.html","/swagger-resources/**","/webjars/**"};

        registry.addInterceptor(loginHandlerInterceptor)
                .addPathPatterns("/**")//表示拦截所有请求
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")//表示除了登录注册之外，因为登录注册不需要登录也可以访问
                .excludePathPatterns(swaggerExcludes);
    }
}
