package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.AppMidFile;
import com.fzt.ktzq.common.appmid.parser.AppMidFileRequest;
import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.CollegeMember;
import com.fzt.ktzq.dao.RspBodyVo;
import com.fzt.ktzq.service.CollegeMemberService;
import com.fzt.ktzq.util.CommConstant;
import com.fzt.ktzq.util.StringUtilsFzt;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 批量上传控制类
 * @author 黄弋峰 2022/12/21
 */
@RestController
public class ImportController {
    private static final Logger logger = LoggerFactory.getLogger(ImportController.class);

    private static final String XLSX = ".xlsx";

    @Autowired
    CollegeMemberService collegeMemberService;

    /**
     * 导入球队成员
     * @param fileReq
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value = "导入球队成员")
    @RequestMapping(value = "/importMember", method = RequestMethod.POST)
    public RspBodyVo importMember(@RequestBody AppMidFileRequest fileReq) throws ServiceException{
        logger.info("开始导入球队成员，请求参数，{}", fileReq.getUploadRequestParams());
        Map<String, String[]> reqMap = fileReq.getUploadRequestParams();
        Assert.notEmpty(reqMap, "请求参数不可为空！！");
        String[] collegeIdArr = reqMap.get("collegeId");
        Assert.notEmpty(collegeIdArr, "请求参数中球队id不可为空！！");
        String collegeIdOrg = collegeIdArr[0];
        Assert.notNull(collegeIdOrg, "请求参数中球队id不能为空！");
        Integer collegeId = Integer.parseInt(collegeIdOrg);
        List<AppMidFile> appMidFileList = fileReq.getUploadAppMidFileList();
        AppMidFile appMidFile = appMidFileList.get(0);
        String fileName = appMidFile.getOriginalFilename();
        if (fileName.endsWith(XLSX)){
            try (InputStream inputStream = appMidFile.getInputStream();
                 Workbook workbook = new XSSFWorkbook(inputStream)) {
                RspBodyVo handleMember = handleMember(collegeId, workbook);
                printErr(handleMember);//无错误不会打印
                return handleMember;
            } catch (ServiceException e){
                throw new ServiceException(CommConstant.ERROR_CODE, e.getDesc());
            } catch (Exception e){
                logger.info("导入成员信息异常！！");
                throw new ServiceException(CommConstant.ERROR_CODE, "导入成员信息异常！！");
            }
        }
        return null;
    }

    /**
     * 处理球队成员导入
     * @param collegeId
     * @param workbook
     * @return
     * @throws ServiceException
     */
    public RspBodyVo handleMember(Integer collegeId, Workbook workbook) throws ServiceException{
        RspBodyVo rspBodyVo = new RspBodyVo();
        //获取表格数据
        Sheet sheetAt = workbook.getSheetAt(0);

        //查询数据库已有的球队成员
        CollegeMember collegeMember = new CollegeMember();
        collegeMember.setCollegeId(collegeId);
        List<CollegeMember> collegeMemberList = collegeMemberService.selectMember(collegeMember);
        //已有成员电话集合
        List<String> orMemberTel = getOrMemberTel(collegeMemberList);
        //这里还需要校验重复，跟已有重复、必填

        return setSuss(rspBodyVo);
    }

    /**
     * 获取已有成员的电话集合
     * @param collegeMemberList
     * @return
     */
    public List<String> getOrMemberTel(List<CollegeMember> collegeMemberList){
        List<String> list = new ArrayList<>();
        if (StringUtilsFzt.isNotEmpty(collegeMemberList)){
            for (CollegeMember collegeMember : collegeMemberList){
                list.add(collegeMember.getTel());
            }
        }
        return list;
    }

    /**
     * 设置成功返回
     * @param rspBodyVo
     * @return
     */
    public RspBodyVo setSuss(RspBodyVo rspBodyVo){
        rspBodyVo.setCode(CommConstant.SUCCESS_CODE);
        rspBodyVo.setMsg(CommConstant.SUCCESS);
        return rspBodyVo;
    }

    /**
     * 打印错误信息
     * @param handleMember
     */
    public void printErr(RspBodyVo handleMember){
        if (!CommConstant.SUCCESS_CODE.equals(handleMember.getCode())){
            List<String> errMsg = handleMember.getErrMsg();
            for (String string : errMsg){
                logger.info("错误信息，{}", string);
            }
        }
    }
}
