package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务类
 * @author 黄弋峰  2022/11/24
 */
@Service
public class UserService {

    @Autowired(required = false)
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
     * 用户注册
     * @param user
     * @return
     */
    @Transactional
    public int insertUser(User user){
        return userMapper.insertSelective(user);
    }

    /**
     * 通过id查询用户
     * @param userSelect
     * @return
     */
    public User selectUser(User userSelect){
        return userMapper.selectOne(userSelect);
    }

    /**
     * 注册用户时校验用
     * @param user
     * @return
     */
    public List<User> checkUser(User user){
        return userMapper.select(user);
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

    /**
     * 通过用户名查询用户
     * @param user
     * @return
     */
    public User findUserByUserName(String user){
        User userName = new User();
        userName.setAccount(user);
        List<User> list = userMapper.select(userName);
        if (list != null){
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 修改用户
     * @param userSelect
     */
    public boolean updateUser(User userSelect){
        boolean flag = false;
        try {
            userMapper.updateByPrimaryKeySelective(userSelect);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
