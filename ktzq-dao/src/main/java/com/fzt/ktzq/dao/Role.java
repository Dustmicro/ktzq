package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色实体类
 * @author 黄弋峰 2022/12/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_role")
public class Role implements Serializable {

    @Id
    private Integer roleId;

    private String roleName;

    private Integer collegeId;

    private Integer aereId;

    private String roleDesc;

    private String mark;

    private String reserve;

    private Integer statusCd;

    private static final long serialVersionUID = 1L;
}