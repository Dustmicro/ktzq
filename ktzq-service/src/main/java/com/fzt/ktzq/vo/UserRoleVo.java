package com.fzt.ktzq.vo;

import com.fzt.ktzq.thoughwork.xstream.XStreamAlias;

import java.io.Serializable;
import java.util.List;

@XStreamAlias("request")
public class UserRoleVo implements Serializable {

    private Long userId;
    private Integer staffId;
    private Long memberId;
    private int tel;
    private List<Integer> roleList;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "UserRoleVo{" +
                "userId=" + userId +
                ", staffId=" + staffId +
                ", memberId=" + memberId +
                ", tel=" + tel +
                ", roleList=" + roleList +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }
}
