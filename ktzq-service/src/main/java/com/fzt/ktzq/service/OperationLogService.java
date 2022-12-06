package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.OperationLog;
import com.fzt.ktzq.mapper.OperationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationLogService {

    @Autowired(required = false)
    OperationLogMapper operationLogMapper;

    public boolean addOperationLog(OperationLog operationLog){
        boolean flag = false;
        try {
            operationLogMapper.insertSelective(operationLog);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
