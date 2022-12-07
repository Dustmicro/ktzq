package com.fzt.ktzq.service;

import com.fzt.ktzq.dao.Program;
import com.fzt.ktzq.mapper.ProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日程服务类
 * @author 黄弋峰 2022/12/5
 */
@Service
public class ProgramService {

    @Autowired(required = false)
    ProgramMapper programMapper;

    /**
     * 查询所有日程
     * @return
     */
    public List<Program> selectProgramAll(){
        return programMapper.selectAll();
    }

    /**
     * 根据条件查询数据
     * @param program
     * @return
     */
    public List<Program> selectProgram(Program program){
        return programMapper.select(program);
    }

    /**
     * 新增日程
     * @param program
     * @return
     */
    public boolean addProgram(Program program){
        boolean flag = false;
        try {
            programMapper.insertSelective(program);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除日程
     * @param program
     * @return
     */
    public boolean deleteProgram(Program program){
        boolean flag = false;
        try {
            programMapper.deleteByPrimaryKey(program);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改日程
     * @param program
     * @return
     */
    public boolean updateProgram(Program program){
        boolean flag = false;
        try {
            programMapper.updateByPrimaryKeySelective(program);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
