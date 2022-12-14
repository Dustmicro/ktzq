package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.Role;
import com.fzt.ktzq.dao.UserRoleMapping;
import com.fzt.ktzq.mapper.UserRoleMappingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限关联类
 * @author 黄弋峰 2022/12/9
 */
@Service
public class UserRoleMappingService {

    @Autowired(required = false)
    UserRoleMappingMapper userRoleMappingMapper;

    /**
     * 通过userId修改员工
     * @param userRoleMapping
     * @return
     */
    public boolean updateByUserId(UserRoleMapping userRoleMapping){
        boolean flag = false;
        try {
            userRoleMappingMapper.updateByUserId(userRoleMapping);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 批量插入
     * @param list
     * @return
     */
    public boolean insertBath(List<UserRoleMapping> list){
        boolean flag = false;
        try {
            userRoleMappingMapper.insertBath(list);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通过userId删除员工
     * @param userId
     * @return
     */
    public boolean deleteByUserId(Long userId){
        boolean flag = false;
        try {
            userRoleMappingMapper.deleteByUserId(userId);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public List<UserRoleMapping> selectByUserId(Long userId){
        return userRoleMappingMapper.selectByUserId(userId);
    }

    public UserRoleMapping selectForUserId(Integer roleId){
        return userRoleMappingMapper.selectForUserId(roleId);
    }

    public List<UserRoleMapping> selectUser(UserRoleMapping userRoleMapping){
        return userRoleMappingMapper.select(userRoleMapping);
    }
}
