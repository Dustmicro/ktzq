package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_user")
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String userId;

    private String userName;

    private Integer age;

    private String tel;

    private Integer sex;

    private String account;

    private String password;

    private Integer permissions;

    private String collegeNum;

    private String collegeName;

    private String aereNum;

    private String aereName;

    private String address;

    private String eMail;

    private String uMark;

    private Integer statusCd;

    private Date createTime;

    private Date updateTime;

    private Integer pswErrNum;

    @Transient
    private String passwordRept;

    private static final long serialVersionUID = 1L;
}