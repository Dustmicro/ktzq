package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.MenuRoleMapping;
import com.fzt.ktzq.mapper.MenuRoleMappingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuRoleMappingService {

    @Autowired(required = false)
    MenuRoleMappingMapper menuRoleMappingMapper;

    /**
     * 批量插入
     * @param list
     * @return
     */
    public boolean insertBath(List<MenuRoleMapping> list){
        boolean flag = false;
        try {
            menuRoleMappingMapper.insertBath(list);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
