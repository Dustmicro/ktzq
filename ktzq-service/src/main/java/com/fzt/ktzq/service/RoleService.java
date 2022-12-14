package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.MenuRoleMapping;
import com.fzt.ktzq.dao.Role;
import com.fzt.ktzq.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务类
 * @author 黄弋峰 2022/12/8
 */
@Service
public class RoleService {

    @Autowired(required = false)
    RoleMapper roleMapper;

    /**
     * 根据条件查询角色
     * @param role
     * @return
     */
    public List<Role> selectRoleList(Role role){
        return roleMapper.select(role);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    public boolean addRole(Role role){
        boolean flag = false;
        try {
            roleMapper.insertSelective(role);
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
    public boolean insertBath(List<Role> list){
        boolean flag = false;
        try {
            roleMapper.insertBath(list);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除角色
     * @param role
     * @return
     */
    public boolean deleteRole(Role role){
        boolean flag = false;
        try {
            roleMapper.deleteByPrimaryKey(role);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    public boolean updateRole(Role role){
        boolean flag = false;
        try {
            roleMapper.updateByPrimaryKeySelective(role);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 单条数据查询
     * @param role
     * @return
     */
    public Role selectRoleForId(Role role){
        return roleMapper.selectOne(role);
    }

    /**
     * 根据roleId查询
     * @param roleId
     * @return
     */
    public Role selectRoleById(Integer roleId){
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
