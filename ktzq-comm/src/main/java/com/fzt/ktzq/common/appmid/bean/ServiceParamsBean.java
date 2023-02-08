package com.fzt.ktzq.common.appmid.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(
        prefix = "service-params"
)

@Component
public class ServiceParamsBean {

    public ServiceParamsBean() {

    }

    private Map<String, String> paramsMap;

    public Map<String, String> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map<String, String> paramsMap) {
        this.paramsMap = paramsMap;
    }
}
