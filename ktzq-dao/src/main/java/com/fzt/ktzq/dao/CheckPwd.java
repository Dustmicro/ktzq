package com.fzt.ktzq.dao;

import java.io.Serializable;

/**
 * 修改密码实体类
 * @author 黄弋峰 2022/12/21
 */
public class CheckPwd implements Serializable {
    private Long userId;
    private String oldPwd;
    private String newPwd;
    private String newPwdRet;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getNewPwdRet() {
        return newPwdRet;
    }

    public void setNewPwdRet(String newPwdRet) {
        this.newPwdRet = newPwdRet;
    }
}
