package com.fzt.ktzq.mapper;


import com.fzt.ktzq.dao.UserRoleMapping;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserRoleMappingMapper extends Mapper<UserRoleMappingMapper> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(UserRoleMapping record);
//
//    int insertSelective(UserRoleMapping record);
//
//    UserRoleMapping selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(UserRoleMapping record);
//
    @Update("update user_role_mapping set role_id = #{roleId} where user_id = #{userId}")
    void updateByUserId(@Param("userId") UserRoleMapping record);

    @Delete("delete from user_role_mapping where user_id = #{userId}")
    void deleteByUserId(@Param("userId") Long userId);

    @Select("select * from user_role_mapping where user_id = #{userId}")
    List<UserRoleMapping> selectByUserId(@Param("userId") Long userId);
//
//    UserRoleMapping find(UserRoleMapping record);
//
//    List<UserRoleMapping> list(UserRoleMapping record);
//
//    Page<UserRoleMapping> pageList(UserRoleMapping record);
}