package com.fzt.ktzq.mapper;

import com.fzt.ktzq.dao.Dictionary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DictionaryMapper extends Mapper<Dictionary> {
//    int deleteByPrimaryKey(Integer dicId);
//
//    int insert(Dictionary record);
//
//    int insertSelective(Dictionary record);
//
//    Dictionary selectByPrimaryKey(Integer dicId);
//
//    int updateByPrimaryKeySelective(Dictionary record);
//
//    int updateByPrimaryKey(Dictionary record);
//
//    Dictionary find(Dictionary record);
//
//     @Select("select * from u_dictionary where dic_type_id like")
//    @Select("select dic_id,dic_name,dic_type_id,status_cd,update_time,create_time,mark" +
//            " from u_dictionary where dic_name like '%#{dicName}%'")
//    List<Dictionary> listSupper(@Param("dicName") String dicName);
//
//    Page<Dictionary> pageList(Dictionary record);
}