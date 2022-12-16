package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.College;
import com.fzt.ktzq.dao.Program;
import com.fzt.ktzq.dao.RestResult;
import com.fzt.ktzq.dao.User;
import com.fzt.ktzq.service.CollegeService;
import com.fzt.ktzq.service.ProgramService;
import com.fzt.ktzq.util.AuthUserContext;
import com.fzt.ktzq.util.CommConstant;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    CollegeService collegeService;

    /**
     * 查询所有日程
     * @return
     */
    @ApiOperation(value = "查询所有日程")
    @GetMapping("/selectProgramAll")
    public List<Program> selectProgramAll(){
        return programService.selectProgramAll();
    }

    /**
     * 新增日程服务
     * @param program
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "新增日程服务")
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
            //如果球队编号不为空
            if (user.getAereNum() != null){
                program.setParentNum(user.getAereNum());
            } else if (user.getCollegeId() != null) {//如果区域编号不为空
                College list = collegeService.find(user.getCollegeId());
                program.setParentNum(list.getCollegeNum());
            }
            program.setCreateUser(user.getUserName());
            programService.addProgram(program);
        } catch (Exception e){
            logger.info("新增日程异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "新增日程异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 删除日程
     * 只有队长、副队长、教练能够对日程进行操作，其余的只能查看
     * @param program
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "删除日程")
    @RequestMapping(value = "/deleteProgram", method = RequestMethod.POST)
    public RestResult<Object> deleteProgram(@RequestBody Program program) throws ServiceException{
        logger.info("删除日程服务开始，请求参数，{}", program);
        try {
            programService.deleteProgram(program);
        } catch (Exception e){
            logger.info("删除日程异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "删除日程异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 修改日程
     * @param program
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "修改日程")
    @RequestMapping(value = "/updateProgram", method = RequestMethod.POST)
    public RestResult<Object> updateProgram(@RequestBody Program program) throws ServiceException{
        logger.info("修改日程服务开始，请求参数，{}", program);
        try {
            programService.updateProgram(program);
        } catch (Exception e){
            logger.info("修改日程异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "修改日程异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }

    /**
     * 根据条件查询日程
     * @param program
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "根据条件查询日程")
    @RequestMapping(value = "/selectProgram", method = RequestMethod.POST)
    public RestResult<Object> selectProgram(@RequestBody Program program) throws ServiceException{
        logger.info("查询日程服务开始，请求参数，{}", program);
        try {
            programService.selectProgram(program);
        } catch (Exception e){
            logger.info("查询日程异常");
            throw new ServiceException(CommConstant.ERROR_CODE, "查询日程异常");
        }
        return RestResult.success(CommConstant.SUCCESS);
    }
}
