package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role_mapping")
public class UserRoleMapping implements Serializable {

    @Id
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Integer statusCd;

    private static final long serialVersionUID = 1L;
}