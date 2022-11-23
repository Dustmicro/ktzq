package com.fzt.ktzq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzt.ktzq.model.Building;
import com.fzt.ktzq.model.User;
import com.github.pagehelper.PageInfo;

/**
 * 用户类
 * @author 黄弋峰
 */
public interface UserService extends IService<User> {

    /**
     * 查询所有用户数据
     * @param page
     * @param pageSize
     * @param numbers
     * @return
     */
    PageInfo<User> findUserAll(int page, int pageSize, String numbers);

    IPage<User> findListByPage(Integer page, Integer pageCount);

    int add(User user);

    int delete(Long id);

    int updateData(User user);

    User findById(Long id);

    User queryUserById(Integer userId);
}
