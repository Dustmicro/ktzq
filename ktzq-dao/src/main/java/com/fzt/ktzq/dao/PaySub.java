package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 收费科目实体类
 * @author 黄弋峰 2023/1/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pay_sub")
public class PaySub implements Serializable {

    @Id
    private Integer id;

    private String subName;

    private String subType;

    private String subCount;

    private String subPay;

    private Date subTime;

    private String statusCd;

    private static final long serialVersionUID = 1L;
}