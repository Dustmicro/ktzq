package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.Staff;
import com.fzt.ktzq.dao.Task;
import com.fzt.ktzq.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务服务类
 * @author 黄弋峰 2023/2/8
 */
@Service
public class TaskService {

    @Autowired(required = false)
    TaskMapper taskMapper;

    /**
     * 查询所有定时任务数据
     * @return
     */
    public List<Task> selectTaskAll(){
        return taskMapper.selectAll();
    }

    /**
     * 查询定时任务
     * @param task
     * @return
     */
    public List<Task> selectTask(Task task) {
        return taskMapper.select(task);
    }

    /**
     * 新增定时任务
     * @param task
     * @return
     */
    public boolean addTask(Task task) {
        boolean flag = false;
        try {
            taskMapper.insertSelective(task);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除定时任务
     * @param task
     * @return
     */
    public boolean deleteTask(Task task) {
        boolean flag = false;
        try {
            taskMapper.deleteByPrimaryKey(task);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改定时任务
     * @param task
     * @return
     */
    public boolean updateTask(Task task) {
        boolean flag = false;
        try {
            taskMapper.updateByPrimaryKeySelective(task);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
