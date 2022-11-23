package com.fzt.ktzq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fzt.ktzq.model.Building;
import com.fzt.ktzq.model.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("UserDao")
public interface UserMapper extends BaseMapper<User> {

    List<User> queryUserAll(@Param("numbers") String numbers);
    /**
     * 通过house中building_id查找building
     */
    User queryUserById(@Param("buildId") Integer userId);

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User find(User record);

    List<User> list(User record);

    Page<User> pageList(User record);
}