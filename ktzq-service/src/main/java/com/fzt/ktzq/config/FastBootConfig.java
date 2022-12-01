package com.fzt.ktzq.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取自定义配置信息
 * @author 黄弋峰  2022/11/28
 */
@Configuration
@ConfigurationProperties(prefix = "skip-interceptor")
public class FastBootConfig {
    private Map<String, List<String>> request = new HashMap<>();

    public Map<String, List<String>> getRequest() {
        return request;
    }

    public void setRequest(Map<String, List<String>> request) {
        this.request = request;
    }
}
