package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.Work;
import com.fzt.ktzq.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职务服务类
 * @author 黄弋峰 2022/12/10
 */
@Service
public class WorkService {

    @Autowired(required = false)
    WorkMapper workMapper;

    /**
     * 查询职务
     * @param work
     * @return
     */
    public List<Work> selectWork(Work work){
        return workMapper.select(work);
    }
}
