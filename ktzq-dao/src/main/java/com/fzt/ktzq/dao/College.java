package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 球队实体类
 * @author 黄弋峰  2022/12/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_college")
public class College implements Serializable {
    @Id
    private Integer id;

    private String collegeNum;

    private String collegeName;

    private String aereNum;

    private String aereName;

    private String address;

    private String tel;

    private String addressxq;

    private Date createTime;

    private String statusCd;

    @Transient
    private List<Integer> addressList;

    private static final long serialVersionUID = 1L;
}