package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 区域实体类
 * @author 黄弋峰  2022/12/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_aere")
public class Aere implements Serializable {

    @Id
    private Integer id;

    private String aereNum;

    private String aereName;

    private Integer userId;

    private String userName;

    private Integer permissions;

    private String tel;

    private String address;

    private Date createTime;

    private String statusCd;

    private static final long serialVersionUID = 1L;
}