package com.fzt.ktzq.mapper;

import com.fzt.ktzq.dao.User;
import com.github.pagehelper.Page;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<User> {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User find(User record);

    List<User> list(User record);

    Page<User> pageList(User record);

    List<User> all();
}