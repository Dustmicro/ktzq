package com.fzt.ktzq.dao;

/**
 * 定时任务工程返回实体类封装
 * @author 黄弋峰 2023/2/8
 */
public class ResponseVo {
    public ResponseVo(){

    }

    private String msg;

    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResponseVo(String msg, String code){
        this.code = code;
        this.msg = msg;
    }
}
