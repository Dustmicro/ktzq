package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.Space;
import com.fzt.ktzq.mapper.SpaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 场地服务类
 * @author 黄弋峰 2023/2/22
 */
@Service
public class SpaceService {

    @Autowired(required = false)
    SpaceMapper spaceMapper;

    /**
     * 查询所有场地信息
     * @return
     */
    public List<Space> selectAllSpace(){
        return spaceMapper.selectAll();
    }

    /**
     * 根据条件查询场地信息
     * @param space
     * @return
     */
    public List<Space> selectSpace(Space space){
        return spaceMapper.select(space);
    }

    /**
     * 新增场地信息
     * @param space
     * @return
     */
    public boolean addSpace(Space space){
        boolean flag = false;
        try {
            spaceMapper.insertSelective(space);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除场地信息
     * @param space
     * @return
     */
    public boolean deleteSpace(Space space){
        boolean flag = false;
        try {
            spaceMapper.deleteByPrimaryKey(space);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改场地信息
     * @param space
     * @return
     */
    public boolean updateSpace(Space space){
        boolean flag = false;
        try {
            spaceMapper.updateByPrimaryKeySelective(space);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
