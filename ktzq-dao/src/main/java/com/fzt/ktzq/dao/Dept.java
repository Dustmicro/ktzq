package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 部门实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_dept")
public class Dept implements Serializable {
    @Id
    private Integer id;

    private String deptId;

    private String deptName;

    private String collegeNum;

    private String aereNum;

    private Integer statusCd;

    private static final long serialVersionUID = 1L;
}