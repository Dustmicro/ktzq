package com.fzt.ktzq.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 器材管理实体类
 * @author 黄弋峰 2023/2/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "space_material")
public class SpaceMaterial implements Serializable {
    private Integer materialId;

    private String materialName;

    private String materialType;

    private String subCount;

    private Integer userId;

    private Date createTime;

    private String statusCd;

    private static final long serialVersionUID = 1L;

//    public Integer getMaterialId() {
//        return materialId;
//    }
//
//    public void setMaterialId(Integer materialId) {
//        this.materialId = materialId;
//    }
//
//    public String getMaterialName() {
//        return materialName;
//    }
//
//    public void setMaterialName(String materialName) {
//        this.materialName = materialName == null ? null : materialName.trim();
//    }
//
//    public String getMaterialType() {
//        return materialType;
//    }
//
//    public void setMaterialType(String materialType) {
//        this.materialType = materialType == null ? null : materialType.trim();
//    }
//
//    public String getSubCount() {
//        return subCount;
//    }
//
//    public void setSubCount(String subCount) {
//        this.subCount = subCount == null ? null : subCount.trim();
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
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