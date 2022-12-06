package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 日程实体类
 * @author  黄弋峰  2022/12/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_program")
public class Program implements Serializable {

    @Id
    private Integer programId;

    private String programName;

    private Integer programType;

    private String programContent;

    private String createUser;

    private String tel;

    private String parentNum;

    private Date createTime;

    private Date updateTime;

    private Integer statusCd;

    private static final long serialVersionUID = 1L;
}