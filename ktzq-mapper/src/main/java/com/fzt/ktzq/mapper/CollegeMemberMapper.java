package com.fzt.ktzq.mapper;

import com.fzt.ktzq.dao.CollegeMember;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CollegeMemberMapper extends Mapper<CollegeMember> {

    @Select("select a.mark,b.member_type_cd from u_dictionary a,college_member b " +
            "where a.dic_name = 'memberType' and a.dic_type_id = b.member_type_cd ")
    List<CollegeMember> selectForType();

}
