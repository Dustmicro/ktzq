package com.fzt.ktzq.mapper;


import com.fzt.ktzq.dao.MenuRoleMapping;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface MenuRoleMappingMapper extends Mapper<MenuRoleMapping>, InsertListMapper<MenuRoleMapping> {
    void insertBath(List<MenuRoleMapping> list);
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(MenuRoleMapping record);
//
//    int insertSelective(MenuRoleMapping record);
//
//    MenuRoleMapping selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(MenuRoleMapping record);
//
//    int updateByPrimaryKey(MenuRoleMapping record);
//
//    MenuRoleMapping find(MenuRoleMapping record);
//
//    List<MenuRoleMapping> list(MenuRoleMapping record);
//
//    Page<MenuRoleMapping> pageList(MenuRoleMapping record);
}