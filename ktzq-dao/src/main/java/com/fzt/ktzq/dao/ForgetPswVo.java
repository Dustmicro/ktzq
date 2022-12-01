package com.fzt.ktzq.dao;

import java.io.Serializable;

/**
 * 忘记密码
 * @author 黄弋峰  2022/11/30
 */
public class ForgetPswVo implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String userId;

    private String password;
    /**重复密码**/
    private String passwordRept;
    private String tel;
    /**验证码**/
    private String verifyCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRept() {
        return passwordRept;
    }

    public void setPasswordRept(String passwordRept) {
        this.passwordRept = passwordRept;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
