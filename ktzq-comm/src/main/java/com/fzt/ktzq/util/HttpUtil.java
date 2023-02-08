package com.fzt.ktzq.util;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static final String SERVICE_ID = "serviceId";
    private static final String COLLEGE_ID = "collegeId";

    private HttpUtil(){

    }

    public static String sendPost(String url, String stringEntity) {
        //获取Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //创建POST请求
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(stringEntity, StandardCharsets.UTF_8);
        //POST的请求是将参数放在请求体里传过去的，这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        //响应模型
        CloseableHttpResponse response = null;
        try {
            //由客户端执行（发送）POST请求
            response = httpClient.execute(httpPost);
            //从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity == null) {
                return null;
            } else {
                return EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("关闭资源异常", e);
            }
        }
        return null;
    }
}
