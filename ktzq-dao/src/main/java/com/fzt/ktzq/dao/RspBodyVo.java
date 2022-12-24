package com.fzt.ktzq.dao;

import java.io.Serializable;
import java.util.List;

public class RspBodyVo implements Serializable {
    private String code;
    private String msg;
    private List<String> errMsg;
    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(List<String> errMsg) {
        this.errMsg = errMsg;
    }
}
