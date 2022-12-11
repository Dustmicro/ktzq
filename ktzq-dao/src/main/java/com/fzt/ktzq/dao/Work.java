package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 职务实体类
 * @author 黄弋峰  2022/12/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_work")
public class Work implements Serializable {

    @Id
    private Integer wId;

    private String wName;

    private static final long serialVersionUID = 1L;
}
