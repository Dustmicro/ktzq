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

    /**子部门集合**/
    private List<Ztree> subList;

    /**是否勾选**/
    private boolean checked = false;

    /**是否展开**/
    private boolean open = false;

    /**是否勾选**/
    private boolean nocheck = false;

    public List<Ztree> getSubList() {
        return subList;
    }

    public void setSubList(List<Ztree> subList) {
        this.subList = subList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getLeveName() {
        return leveName;
    }

    public void setLeveName(String leveName) {
        this.leveName = leveName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public List<MenuFunBean> getFunBeanList() {
        return funBeanList;
    }

    public void setFunBeanList(List<MenuFunBean> funBeanList) {
        this.funBeanList = funBeanList;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }
}