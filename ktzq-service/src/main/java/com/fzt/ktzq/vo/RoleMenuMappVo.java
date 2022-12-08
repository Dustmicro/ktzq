package com.fzt.ktzq.vo;

import com.fzt.ktzq.thoughwork.xstream.XStreamAlias;

import java.io.Serializable;
import java.util.List;

@XStreamAlias("request")
public class RoleMenuMappVo implements Serializable {

    int roleId;

    private List<Integer> funList;

    private List<Integer> menuIdList;

    private static final long serialVersionUID = 1L;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getFunList() {
        return funList;
    }

    public void setFunList(List<Integer> funList) {
        this.funList = funList;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
