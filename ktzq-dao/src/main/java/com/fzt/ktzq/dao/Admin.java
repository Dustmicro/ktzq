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
@Table(name = "u_admin")
public class Admin implements Serializable {
    @Id
    private Integer id;
    private String userName;
    private Integer collegeId;
    private String collegeName;
    private Integer aereId;
    private String aereName;
    private Integer roleId;
    private String roleName;
    private String tel;
    private String address;
    private String state;
    private String status;
    private String statusCd;

}
