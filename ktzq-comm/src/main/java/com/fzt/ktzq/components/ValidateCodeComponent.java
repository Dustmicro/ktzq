package com.fzt.ktzq.components;

import com.fzt.ktzq.Login.ILoginServiceSMO;
import com.fzt.ktzq.dao.IPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 验证码组件
 * @author 黄弋峰 2022/12/30
 */
@Component("validate-code")
public class ValidateCodeComponent {

    @Autowired(required = false)
    private ILoginServiceSMO loginServiceSMOImpl;

    /**
     * 生成 验证码
     *
     * @param pd
     * @return
     */
    public ResponseEntity<String> generateValidateCode(IPageData pd) {

        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = loginServiceSMOImpl.generateValidateCode(pd);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
        }
        
        return responseEntity;
    }

    /**
     * 校验验证码
     *
     * @param pd
     * @return
     */
    public ResponseEntity<String> validate(IPageData pd) {
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = loginServiceSMOImpl.validate(pd);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
        }
        
        return responseEntity;
    }


    public ILoginServiceSMO getLoginServiceSMOImpl() {
        return loginServiceSMOImpl;
    }

    public void setLoginServiceSMOImpl(ILoginServiceSMO loginServiceSMOImpl) {
        this.loginServiceSMOImpl = loginServiceSMOImpl;
    }
}
