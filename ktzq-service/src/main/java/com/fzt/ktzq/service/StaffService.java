package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.Staff;
import com.fzt.ktzq.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工服务类
 * @author 黄弋峰 2022/12/7
 */
@Service
public class StaffService {

    @Autowired(required = false)
    StaffMapper staffMapper;

    /**
     * 查询所有员工数据
     * @return
     */
    public List<Staff> selectStaffAll(){
        return staffMapper.selectAll();
    }

    /**
     * 根据条件查询员工
     * @param staff
     * @return
     */
    public List<Staff> selectStaff(Staff staff){
        return staffMapper.select(staff);
    }

    /**
     * 新增员工
     * @param staff
     * @return
     */
    public boolean addStaff(Staff staff){
        boolean flag = false;
        try {
            staffMapper.insertSelective(staff);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改员工
     * @param staff
     * @return
     */
    public boolean updateStaff(Staff staff){
        boolean flag = false;
        try {
            staffMapper.updateByPrimaryKeySelective(staff);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除员工
     * @param staff
     * @return
     */
    public boolean deleteStaff(Staff staff){
        boolean flag = false;
        try {
            staffMapper.deleteByPrimaryKey(staff);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
