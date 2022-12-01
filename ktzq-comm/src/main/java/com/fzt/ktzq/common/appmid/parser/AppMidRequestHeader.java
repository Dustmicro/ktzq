package com.fzt.ktzq.common.appmid.parser;

import com.fzt.ktzq.thoughwork.xstream.XStreamAlias;

@XStreamAlias("transactionHeader")
public class AppMidRequestHeader {
    private String serviceId;
    private String requestDate;
    private String requestTime;
    private String operatorNo;
    private String txSN;
    private String channelId;
    private String mediaType;
    private Integer pageNo;
    private Integer pageSize;

    public AppMidRequestHeader(){

    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getTxSN() {
        return txSN;
    }

    public void setTxSN(String txSN) {
        this.txSN = txSN;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "AppMidRequestHeader{" +
                "serviceId='" + serviceId + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", operatorNo='" + operatorNo + '\'' +
                ", txSN='" + txSN + '\'' +
                ", channelId='" + channelId + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
