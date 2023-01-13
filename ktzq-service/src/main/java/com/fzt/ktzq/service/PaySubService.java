package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.PaySub;
import com.fzt.ktzq.mapper.PaySubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收费科目服务类
 * @author 黄弋峰 2023/1/13
 */
@Service
public class PaySubService {

    @Autowired(required = false)
    PaySubMapper paySubMapper;

    /**
     * 查询所有科目
     * @return
     */
    public List<PaySub> selectPaySubAll(){
        return paySubMapper.selectAll();
    }

    /**
     * 新增收费科目
     * @param paySub
     * @return
     */
    public boolean addPaySub(PaySub paySub){
        boolean flag = false;
        try {
            paySubMapper.insertSelective(paySub);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除收费科目
     * @param paySub
     * @return
     */
    public boolean deletePaySub(PaySub paySub){
        boolean flag = false;
        try {
            paySubMapper.delete(paySub);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改收费科目
     * @param paySub
     * @return
     */
    public boolean updatePaySub(PaySub paySub){
        boolean flag = false;
        try {
            paySubMapper.updateByPrimaryKeySelective(paySub);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

}
