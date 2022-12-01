package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.Dept;
import com.fzt.ktzq.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门服务类
 * @author 黄弋峰  2022/12/1
 */

@Service
public class DeptService {

    @Autowired(required = false)
    private DeptMapper deptMapper;

    /**
     * 新增学院下的部门
     * @param dept
     * @return
     */
    public boolean addDeptByCollege(Dept dept){
        boolean flag = false;
        try {
            deptMapper.insertSelective(dept);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 新增区域下的部门
     * @param dept
     * @return
     */
    public boolean addDeptByAere(Dept dept){
        boolean flag = false;
        try {
            deptMapper.insertSelective(dept);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除部门
     * @param dept
     * @return
     */
    public boolean deleteDept(Dept dept){
        boolean flag = false;
        try {
            deptMapper.deleteByPrimaryKey(dept);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改部门
     * @param dept
     * @return
     */
    public boolean updateDept(Dept dept){
        boolean flag = false;
        try {
            deptMapper.updateByPrimaryKeySelective(dept);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有部门
     * @return
     */
    public List<Dept> selectDeptAll(){
        return deptMapper.selectAll();
    }

    /**
     * 根据条件查询部门
     * @param dept
     * @return
     */
    public List<Dept> selectDept(Dept dept){
        return deptMapper.select(dept);
    }
}
