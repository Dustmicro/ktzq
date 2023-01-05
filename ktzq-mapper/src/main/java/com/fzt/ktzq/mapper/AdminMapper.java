package com.fzt.ktzq.mapper;

import com.fzt.ktzq.dao.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AdminMapper extends Mapper<Admin> {

    @Select("SELECT t.college_id,t.college_name,t.address FROM u_college t LEFT JOIN u_aere s ON t.aere_num = s.aere_num WHERE t.aere_num = #{aereNum}")
    List<Admin> selectAdminForQD(@Param("aereNum") Admin admin);
}
