package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 菜单角色（权限）关联表实体类
 * @author 黄弋峰 2022/12/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_role_mapping")
public class MenuRoleMapping implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer menuId;

    private Integer statusCd;

    private static final long serialVersionUID = 1L;
}