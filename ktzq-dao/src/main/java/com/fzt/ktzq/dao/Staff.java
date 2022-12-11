package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 员工实体类
 * @author 黄弋峰 2022/12/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_staff")
public class Staff implements Serializable {

    @Id
    private Integer staffId;

    private Long userId;

    private String staffNum;

    private String staffName;

    private String tel;

    private String parentDept;

    private Integer roleId;

    private String email;

    private Integer sex;

    private Integer age;

    private String address;

    private String statusCd;

    @Transient
    /**原密码**/
    private String orPassword;

    @Transient
    /**新密码**/
    private String newPassword;

    @Transient
    /**重复密码**/
    private String newPasswordRe;

    @Transient
    private String deptId;

    private static final long serialVersionUID = 1L;
}