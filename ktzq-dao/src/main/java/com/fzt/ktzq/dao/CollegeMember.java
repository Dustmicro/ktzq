package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 球队成员实体类
 * @author 黄弋峰 2022/12/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "college_member")
public class CollegeMember implements Serializable {

    @Id
    private Long memberId;

    private String name;

    private Integer age;

    private Integer sex;

    private String tel;

    private Integer collegeId;

    private String memberTypeCd;

    private Date updateTime;

    private Date startTime;

    private String state;

    private String statusCd;

    private static final long serialVersionUID = 1L;
}