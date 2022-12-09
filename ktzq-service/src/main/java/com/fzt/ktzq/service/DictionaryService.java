package com.fzt.ktzq.service;

import com.fzt.ktzq.common.appmid.parser.ServiceException;
import com.fzt.ktzq.dao.Dictionary;
import com.fzt.ktzq.mapper.DictionaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 字典值服务
 * @author 黄弋峰 2022/11/27
 */
@Service
public class DictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    @Autowired(required = false)
    private DictionaryMapper dictionaryMapper;

    /**
     * 新增字典值
     * @param dictionary
     * @return
     * @throws ServiceException
     */
    public boolean addDictionary(Dictionary dictionary) throws ServiceException {
        boolean flag = false;
        try {
            dictionaryMapper.insertSelective(dictionary);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除字典值
     * @param dictionary
     * @return
     * @throws ServiceException
     */
    public boolean deleteDictionary(Dictionary dictionary) throws ServiceException{
        boolean flag = false;
        try {
            dictionaryMapper.deleteByPrimaryKey(dictionary);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改字典值
     * @param dictionary
     * @return
     * @throws ServiceException
     */
    public boolean updateDictionary(Dictionary dictionary) throws ServiceException{
        boolean flag = false;
        try {
            dictionaryMapper.updateByPrimaryKeySelective(dictionary);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据条件查询字典值
     * @param dictionary
     * @return
     * @throws ServiceException
     */
    public List<Dictionary> selectDictionary(Dictionary dictionary) {
//        Example example = new Example(Dictionary.class);
//        Example.Criteria criteria = example.createCriteria();
//        //按名字模糊查询
//        criteria.andLike("dic_name", "%" + dictionary.getDicName() + "%");
        List<Dictionary> list = dictionaryMapper.select(dictionary);
        return list;
    }

    /**
     * 根据dicTypeId模糊查询
     * @param dictionary
     * @return
     */
    public List<Dictionary> findDictionary(Dictionary dictionary){
        Example example = createExample(dictionary);
        return dictionaryMapper.selectByExample(example);
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<Dictionary> selectAll(){
        return dictionaryMapper.selectAll();
    }

    /**
     * 条件构造器（模糊查询条件构造）
     * @param dictionary
     * @return
     */
    public Example createExample(Dictionary dictionary){
        Example example = new Example(Dictionary.class);
        Example.Criteria criteria = example.createCriteria();
        //按名字模糊查询
        criteria.andLike("dic_name", "%" + dictionary.getDicName() + "%");
        //按id正排序
        example.setOrderByClause("dic_id");
        /***
         * Example 是自定义条件对象
         * criteria  是条件构造器
         * 上面形式 固定写法
         */
//        if (dictionary != null){
//            /***
//             * criteria 条件构造
//             * property  数据库的属性名称
//             * 第二个，传入sql语句的属性
//             */
//            if (!StringUtilsFzt.isEmpty(dictionary.getDicName())){
//                criteria.andLike("dicName","%" + dictionary.getDicName() + "%");
//            }
//        }
        return example;
    }

}
