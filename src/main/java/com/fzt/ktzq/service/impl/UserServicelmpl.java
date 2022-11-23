package com.fzt.ktzq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzt.ktzq.dao.UserMapper;
import com.fzt.ktzq.model.User;
import com.fzt.ktzq.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapperDao;

    @Override
    public PageInfo<User> findUserAll(int page, int pageSize, String numbers) {
        PageHelper.startPage(page,pageSize);
        List<User> list=userMapperDao.queryUserAll(numbers);
        return new PageInfo<>(list);
    }

    @Override
    public IPage<User> findListByPage(Integer page, Integer pageCount){
        IPage<User> wherePage = new Page<>(page, pageCount);
        User where = new User();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(User user){
        return baseMapper.insert(user);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(User user){
        return baseMapper.updateById(user);
    }

    @Override
    public User findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public User queryUserById(Integer userId) {
        return userMapperDao.queryUserById(userId);
    }
}
