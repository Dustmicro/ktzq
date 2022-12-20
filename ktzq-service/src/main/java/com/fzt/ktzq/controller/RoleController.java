package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.MenuRoleMapping;
import com.fzt.ktzq.dao.Role;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.dao.UserRoleMapping;
import com.fzt.ktzq.service.MenuRoleMappingService;
import com.fzt.ktzq.service.RoleService;
import com.fzt.ktzq.service.UserRoleMappingService;
import com.fzt.ktzq.util.AuthUserContext;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.StringUtilsFzt;
import com.fzt.ktzq.vo.RoleMenuMappVo;
import com.github.pagehelper.page.PageMethod;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    UserRoleMappingService userRoleMappingService;

    /**
     * 新增角色
     * @param roleMenuMappVo
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "新增角色服务")
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

    /**
     * 查询角色服务
     * @param role
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "查询角色服务")
    @RequestMapping(value = "/selectRole", method = RequestMethod.POST)
    public List<Role> selectRole(@RequestBody Role role) throws ServiceException{
        logger.info("开始查询角色，请求参数，{}", role);
        List<Role> list = new ArrayList<>();
        try {
            PageMethod.startPage(role.getPageNo(), role.getPageSize());
            list = roleService.selectRoleList(role);
            //在这里添加分页
            return list;
        } catch (Exception e){
            logger.info("查询角色信息异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询角色信息异常");
        }
    }

    /**
     * 根据当前用户查询角色
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "根据当前用户查询角色")
    @RequestMapping(value = "/getRoleByUserId", method = RequestMethod.POST)
    public List<Role> getRoleByUserId() throws ServiceException{
        logger.info("开始获取当前用户角色");
        List<Role> list = new ArrayList<>();
        try {
            User user = AuthUserContext.getUser();
            List<UserRoleMapping> roleList = userRoleMappingService.selectByUserId(user.getUserId());
            if (roleList != null && !roleList.isEmpty()){
                for (UserRoleMapping param : roleList){
                    Role role = new Role();
                    role.setRoleId(param.getRoleId());
                    role = roleService.selectRoleForId(role);
                    list.add(role);
                }
            }
        } catch (Exception e){
            logger.info("查询角色信息异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询角色信息异常");
        }
        return list;
    }

    /**
     * 删除角色服务
     * @param role
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "删除角色服务")
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public String deleteRole(@RequestBody Role role) throws ServiceException{
        logger.info("开始删除角色，请求参数，{}", role);
        try {
            //这里应该加入一些校验
            roleService.deleteRole(role);
        } catch (Exception e){
            logger.info("查询角色信息异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询角色信息异常");
        }
        return CommConstant.SUCCESS;
    }
}
