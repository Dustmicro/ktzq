package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.CollegeMember;
import com.fzt.ktzq.mapper.CollegeMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 球队成员服务类
 * @author 黄弋峰 2022/12/13
 */
@Service
public class CollegeMemberService {

    @Autowired(required = false)
    CollegeMemberMapper collegeMemberMapper;

    /**
     * 查询球队成员类型
     * @return
     */
    public List<CollegeMember> selectMemberType(){
        return collegeMemberMapper.selectForType();
    }

    /**
     * 新增球队成员
     * @param collegeMember
     * @return
     */
    public boolean addMember(CollegeMember collegeMember){
        boolean flag = false;
        try {
            collegeMemberMapper.insertSelective(collegeMember);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改球队成员
     * @param collegeMember
     * @return
     */
    public boolean updateMember(CollegeMember collegeMember){
        boolean flag = false;
        try {
            collegeMemberMapper.updateByPrimaryKeySelective(collegeMember);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除球队成员
     * @param collegeMember
     * @return
     */
    public boolean deleteMember(CollegeMember collegeMember){
        boolean flag = false;
        try {
            collegeMemberMapper.deleteByPrimaryKey(collegeMember);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询球队成员信息
     * @param collegeMember
     * @return
     */
    public List<CollegeMember> selectMember(CollegeMember collegeMember){
        return collegeMemberMapper.select(collegeMember);
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<CollegeMember> selectMemberAll(){
        return collegeMemberMapper.selectAll();
    }

}
