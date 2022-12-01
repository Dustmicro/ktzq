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
@Table(name = "u_dictionary")
public class Dictionary implements Serializable {

//    @KeySql(useGeneratedKeys = true)
    @Id
    private Integer dicId;

    private String dicName;

    private String dicTypeId;

    @Transient
    private String statusCd;

    private Date updateTime;

    private Date createTime;

    private String mark;
    @Transient
    private Integer page;
    @Transient
    private Integer size;

    private static final long serialVersionUID = 1L;
}