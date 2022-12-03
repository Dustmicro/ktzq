package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.Aere;
import com.fzt.ktzq.mapper.AereMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 区域服务类
 * @author 黄弋峰  2022/12/3
 */
@Service
public class AereService {

    @Autowired(required = false)
    AereMapper aereMapper;

    /**
     * 查询所有区域数据
     * @return
     */
    public List<Aere> selectAereAll(){
        return aereMapper.selectAll();
    }

    /**
     * 根据条件查询区域
     * @param aere
     * @return
     */
    public List<Aere> selectAere(Aere aere){
        return aereMapper.select(aere);
    }

    /**
     * 新增区域
     * @param aere
     * @return
     */
    public boolean addAere(Aere aere){
        boolean flag = false;
        try {
            aereMapper.insertSelective(aere);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改区域
     * @param aere
     * @return
     */
    public boolean updateAere(Aere aere){
        boolean flag = false;
        try {
            aereMapper.updateByPrimaryKeySelective(aere);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除区域
     * @param aere
     * @return
     */
    public boolean deleteAere(Aere aere){
        boolean flag = false;
        try {
            aereMapper.deleteByPrimaryKey(aere);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
