package com.fzt.ktzq.vo;

import com.fzt.ktzq.dao.MenuFunBean;

import java.io.Serializable;
import java.util.List;

/**
 * 树状结构实体类
 * @author 黄弋峰 2023/2/10
 */
public class Ztree implements Serializable {

    /**节点id**/
    private int id;

    /**部门id**/
    private Integer deptId;

    private String mType;

    /**节点名称**/
    private String leveName;

    /**节点父id**/
    private int pId;

    /**父节点名称**/
    private String pName;

    /**节点名称**/
    private String name;

    /**节点标题**/
    private String title;

    /**顺序**/
    private int seq;

    /**功能集合**/
    private List<MenuFunBean> funBeanList;

    /**是否勾选**/
    private boolean checked = false;

    /**是否展开**/
    private boolean open = false;

    /****/

}