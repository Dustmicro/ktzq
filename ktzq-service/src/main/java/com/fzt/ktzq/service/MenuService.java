package com.fzt.ktzq.service;

import com.fzt.ktzq.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单服务类
 * @author 黄弋峰 2023/2/10
 */
@Service
public class MenuService {

    @Autowired(required = false)
    MenuMapper menuMapper;
}
