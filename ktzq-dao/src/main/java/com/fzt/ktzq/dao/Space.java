package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 场地实体类
 * @author 黄弋峰 2023/2/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "space")
public class Space implements Serializable {

    @Id
    private Integer spaceId;

    private Integer spaceNum;

    private String spaceName;

    private String mark;

    private String backup1;

    private String backup2;

    private String statusCd;

    private static final long serialVersionUID = 1L;

//    public Integer getSpaceId() {
//        return spaceId;
//    }
//
//    public void setSpaceId(Integer spaceId) {
//        this.spaceId = spaceId;
//    }
//
//    public Integer getSpaceNum() {
//        return spaceNum;
//    }
//
//    public void setSpaceNum(Integer spaceNum) {
//        this.spaceNum = spaceNum;
//    }
//
//    public String getSpaceName() {
//        return spaceName;
//    }
//
//    public void setSpaceName(String spaceName) {
//        this.spaceName = spaceName == null ? null : spaceName.trim();
//    }
//
//    public String getMark() {
//        return mark;
//    }
//
//    public void setMark(String mark) {
//        this.mark = mark == null ? null : mark.trim();
//    }
//
//    public String getBackup1() {
//        return backup1;
//    }
//
//    public void setBackup1(String backup1) {
//        this.backup1 = backup1 == null ? null : backup1.trim();
//    }
//
//    public String getBackup2() {
//        return backup2;
//    }
//
//    public void setBackup2(String backup2) {
//        this.backup2 = backup2 == null ? null : backup2.trim();
//    }
//
//    public String getStatusCd() {
//        return statusCd;
//    }
//
//    public void setStatusCd(String statusCd) {
//        this.statusCd = statusCd == null ? null : statusCd.trim();
//    }
}