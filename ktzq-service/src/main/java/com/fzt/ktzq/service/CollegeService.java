package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.College;
import com.fzt.ktzq.mapper.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 球队服务类
 * @author 黄弋峰 2022/12/2
 */
@Service
public class CollegeService {

    @Autowired(required = false)
    CollegeMapper collegeMapper;

    /**
     * 查询球队
     * @param college
     * @return
     */
    public List<College> selectCollege(College college){
        return collegeMapper.select(college);
    }

    /**
     * 通过学院编号查询学院
     * @param collegeNum
     * @return
     */
    public List<College> selectByCollegeNum(String collegeNum){
        College id = new College();
        id.setCollegeNum(collegeNum);
        return collegeMapper.select(id);
    }

    /**
     * 通过区域编号查询学院
     * @param aereNum
     * @return
     */
    public List<College> selectByAereNum(String aereNum){
        College id = new College();
        id.setAereNum(aereNum);
        return collegeMapper.select(id);
    }

    /**
     * 新增球队
     * @param college
     * @return
     */
    public boolean addCollege(College college){
        boolean flag = false;
        try {
            collegeMapper.insertSelective(college);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
