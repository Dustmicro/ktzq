package com.fzt.ktzq.mapper;


import com.fzt.ktzq.dao.MenuRoleMapping;
import com.fzt.ktzq.dao.Role;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;


public interface RoleMapper extends Mapper<Role>, InsertListMapper<Role> {

    void insertBath(List<Role> list);
//    int deleteByPrimaryKey(Integer roleId);
//
//    int insert(Role record);
//
//    int insertSelective(Role record);
//
//    Role selectByPrimaryKey(Integer roleId);
//
//    int updateByPrimaryKeySelective(Role record);
//
//    int updateByPrimaryKey(Role record);
//
//    Role find(Role record);
//
//    List<Role> list(Role record);
//
//    Page<Role> pageList(Role record);
}