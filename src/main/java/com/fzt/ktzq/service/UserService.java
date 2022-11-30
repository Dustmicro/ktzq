package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.mapper.UserMapper;
import com.fzt.ktzq.util.StringUtilsFzt;
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
     * 通过id查询用户
     * @param user
     * @return
     */
    public List<User> selectUser(User user){
        return userMapper.list(user);
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
     * @param username
     * @return
     */
    public User findUserByUserName(String username){
        User user = new User();
        user.setUserName(username);
        List<User> list = userMapper.findUserByUserName(user.getUserName());
        if (StringUtilsFzt.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 修改用户
     * @param userSelect
     */
    @Transactional
    public void updateUser(User userSelect){
        userMapper.updateByPrimaryKeySelective(userSelect);
    }
}
