package com.fzt.ktzq.common.appmid.parser;

public class ServiceException extends Exception {
    private String code;
    private String desc;
    private AppMidRequestHeader requestHeader;
    private static final long serialVersionUID = 1L;

    public ServiceException(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public ServiceException(AppMidRequestHeader requestHeader, String code, String desc){
        this.code = code;
        this.desc = desc;
        this.requestHeader = requestHeader;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMessage(){
        return "[" + this.code + "] : " + this.desc;
    }

    public AppMidRequestHeader getRequestHeader() {
        return this.requestHeader;
    }

    public void setRequestHeader(AppMidRequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }
}
