package com.fzt.ktzq.controller;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.Space;
import com.fzt.ktzq.service.SpaceService;
import com.fzt.ktzq.util.CommConstant;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 场地服务控制类
 * @author 黄弋峰 2023/2/22
 */
@RestController
public class SpaceController {
    private static final Logger logger = LoggerFactory.getLogger(SpaceController.class);

    @Autowired
    SpaceService spaceService;

    /**
     * 查询所有场地数据信息
     * @return
     */
    @ApiOperation("查询所有场地数据信息")
    @GetMapping("/selectAllSpace")
    public List<Space> selectAllSpace() {
        return spaceService.selectAllSpace();
    }

    /**
     * 根据条件查询场地数据信息
     * @param space
     * @return
     * @throws ServiceException
     */
    @ApiOperation("根据条件查询场地数据信息")
    @RequestMapping(value = "/selectSpace", method = RequestMethod.POST)
    public List<Space> selectSpace(@RequestBody Space space) throws ServiceException {
        logger.info("查询场地信息服务开始，请求参数，{}", space);
        try {
            /**
             * 优化处理：1、添加分页查询
             *         2、必输项字段校验
             *         3、归属人信息校验
             *         4、场地状态筛查
             */
            List<Space> list = spaceService.selectSpace(space);
            return list;
        } catch (Exception e){
            logger.info("场地信息查询失败");
            throw new ServiceException(CommConstant.ERROR_CODE, "场地信息查询失败");
        }
    }
}
