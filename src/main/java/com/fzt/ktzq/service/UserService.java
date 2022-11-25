package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务类
 * @author 黄弋峰  2022/11/24
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User>findAll(){
        return userMapper.selectAll();
    }


    /**
     * 查询所有用户
     * @return
     */
    public List<User> getAll(){
        return userMapper.all();
    }

    /**
     * 新增用户服务
     * @param user
     * @return
     */
    public boolean insert(User user){
        boolean flag = false;
        try {
            userMapper.insert(user);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通过id查询用户
     * @param userId
     * @return
     */
    public User selectUser(String userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 根据ID删除数据库中信息
     * @param userId
     * @return
     */
    public boolean deleteById(String userId){
        boolean flag = false;
        try{
            userMapper.deleteByPrimaryKey(userId);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
