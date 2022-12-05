package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.Program;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.service.ProgramService;
import com.fzt.ktzq.util.AuthUserContext;
import com.fzt.ktzq.util.CommConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 日程控制类
 * @author 黄弋峰 2022/12/5
 */
@RestController
public class ProgramController {
    private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);

    @Autowired
    ProgramService programService;

    /**
     * 查询所有日程
     * @return
     */
    public List<Program> selectProgram(){
        return programService.selectProgramAll();
    }

    /**
     * 新增日程服务
     * @param program
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/addProgram", method = RequestMethod.POST)
    public RestResult<Object> addProgram(@RequestBody Program program) throws ServiceException{
        logger.info("新增日程服务开始，请求参数，{}", program);
        User user = AuthUserContext.getUser();
        Assert.notNull(program.getProgramName(), "日程名称不可为空");
        Assert.notNull(program.getProgramType(), "日程类型不可为空");
        Assert.notNull(program.getProgramContent(), "日程内容不可为空");
//        Assert.notNull(program.getCreateUser(), "创建人不可为空");
        Assert.notNull(program.getTel(), "创建人电话不可为空");
        try {
            program.setCreateUser(user.getUserName());
            programService.addProgram(program);
        } catch (Exception e){
            logger.info("新增日程异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增日程异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }
}
