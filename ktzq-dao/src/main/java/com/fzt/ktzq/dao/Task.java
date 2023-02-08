package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务实体类
 * @author 黄弋峰 2023/2/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_task")
public class Task implements Serializable {

    @Id
    private Integer taskId;

    private String taskName;

    private String classBean;

    private String taskCron;

    private String state;

    private String statusCd;

    private Date createTime;

    @Transient
    private Integer pageNo;

    @Transient
    private Integer pageSize;

    private static final long serialVersionUID = 1L;

}