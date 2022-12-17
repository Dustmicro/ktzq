package com.fzt.ktzq.dao;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModelProperty;
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
    private Long userId;

    @ApiModelProperty(value = "自建应用的agentId", required = true)
    private String userName;

    private Integer age;

    private String tel;

    private Integer sex;

    private String account;

    private String password;

    private Integer roleId;

    private Integer collegeId;

    private String collegeName;

    private String aereNum;

    private String aereName;

    private String address;

    private String eMail;

    private String uMark;

    private String statusCd;

    private Date createTime;

    private Date updateTime;

    private Integer pswErrNum;

    @Transient
    private String passwordRept;

    private Integer logId;

    private static final long serialVersionUID = 1L;
}