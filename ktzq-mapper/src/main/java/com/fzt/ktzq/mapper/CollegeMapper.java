package com.fzt.ktzq.mapper;

import com.fzt.ktzq.dao.College;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface CollegeMapper extends Mapper<College> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(College record);
//
//    int insertSelective(College record);
//
//    College selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(College record);
//
//    int updateByPrimaryKey(College record);
//
      @Select("select * from u_college where college_name = #{collegeName}")
      College checkName(@Param("collegeName") College college);
//
//    List<College> list(College record);
//
//    Page<College> pageList(College record);
}