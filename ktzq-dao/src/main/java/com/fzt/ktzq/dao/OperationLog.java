package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_operationLog")
public class OperationLog implements Serializable {

    @Id
    private Integer operationId;

    private Long userId;

    private String userName;

    private String serviceCode;

    private Date oprateTime;

    private String serviceName;

    private Date startTime;

    private Date endTime;

    private Integer collegeId;

    private Integer aereId;
}
