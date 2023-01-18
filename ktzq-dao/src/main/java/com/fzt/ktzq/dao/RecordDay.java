package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 每日考勤实体类
 * @author 黄弋峰 2023/1/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "record_day")
public class RecordDay implements Serializable {

    @Id
    private Integer recordId;

    private Integer memberId;

    private String state;

    private Date createTime;

    private String mark;

    private String statusCd;

    @Transient
    private String collegeMemberName;

    private static final long serialVersionUID = 1L;

}