package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.MenuRoleMapping;
import com.fzt.ktzq.dao.Role;
import com.fzt.ktzq.service.RoleService;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.StringUtilsFzt;
import com.fzt.ktzq.vo.RoleMenuMappVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色（权限）服务控制类
 * @author 黄弋峰 2022/12/8
 */
@RestController
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @Autowired
    MenuRoleMappingService menuRoleMappingService;

    /**
     * 新增角色
     * @param roleMenuMappVo
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public String addRole(@RequestBody RoleMenuMappVo roleMenuMappVo) throws ServiceException{
        logger.info("开始新增角色，请求参数，{}", roleMenuMappVo);
        try {
            Role role = new Role();
            BeanUtils.copyProperties(roleMenuMappVo, role);
            Assert.notNull(role.getRoleName(), "角色名不能为空");
            if (role.getCollegeId() == null && role.getAereId() == null){
                throw new ServiceException(CommConstant.ERROR_CODE, "球队id和区域id不能同时为空");
            }
            Role param = new Role();
            if (role.getCollegeId() != null){
                //添加球队下角色
                param.setCollegeId(role.getCollegeId());
                param.setRoleName(role.getRoleName());
                if (StringUtilsFzt.isNotEmpty(roleService.selectRoleList(param))){
                    throw new ServiceException(CommConstant.ERROR_CODE, "角色名重复");
                } else {
                    //添加区域下角色
                    param.setAereId(role.getAereId());
                    param.setRoleName(role.getRoleName());
                    role.setCollegeId(null);
                    if (StringUtilsFzt.isNotEmpty(roleService.selectRoleList(param))){
                        throw new ServiceException(CommConstant.ERROR_CODE, "角色名重复");
                    }
                }
                roleService.addRole(role);
                Role forRoleId = roleService.selectRoleForId(role);
                int roleId = forRoleId.getRoleId();
                List<Integer> menuIdList = roleMenuMappVo.getMenuIdList();
                List<MenuRoleMapping> list = new ArrayList<>();
                if (StringUtilsFzt.isNotEmpty(menuIdList)){
                    for (Integer menuId : menuIdList){
                        MenuRoleMapping mapping = new MenuRoleMapping();
                        mapping.setRoleId(roleId);
                        mapping.setMenuId(menuId);
                        list.add(mapping);
                    }
                    menuRoleMappingService.insertBath(list);
                    //在这里还应该再处理角色功能关系
                }
            }
        } catch (ServiceException e){
            throw new ServiceException(CommConstant.ERROR_CODE, e.getDesc());
        } catch (Exception e){
            logger.info("新增角色异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增角色异常");
        }
        return CommConstant.SUCCESS;
    }
}
