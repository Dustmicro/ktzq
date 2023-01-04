package com.fzt.ktzq.dao;


import com.github.pagehelper.Page;

import java.io.Serializable;

/**
 * 分页工具
 * @author 黄弋峰 2023/1/4
 * @param <E>
 */
public class RspPage<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int pageNum;

    private int pageSize;

    /**起始行**/
    private int startRow;

    /**末行**/
    private int endRow;

    /**总数**/
    private long total;

    /**总页数**/
    private int pages;

    /**分页合理化**/
    private Boolean reasonable;

    private Boolean pageSizeZero;

    /**进行count查询时**/
    private String countColumn;

    /**排序**/
    private String orderBy;

    /**只增加排序**/
    private Boolean orderByOnly;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Boolean getReasonable() {
        return reasonable;
    }

    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }

    public Boolean getPageSizeZero() {
        return pageSizeZero;
    }

    public void setPageSizeZero(Boolean pageSizeZero) {
        this.pageSizeZero = pageSizeZero;
    }

    public String getCountColumn() {
        return countColumn;
    }

    public void setCountColumn(String countColumn) {
        this.countColumn = countColumn;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getOrderByOnly() {
        return orderByOnly;
    }

    public void setOrderByOnly(Boolean orderByOnly) {
        this.orderByOnly = orderByOnly;
    }

    public static <E> RspPage<E> getRspPage(Page<E> page){
        RspPage<E> rsp = new RspPage<>();
        rsp.countColumn = page.getCountColumn();
        rsp.endRow = page.getEndRow();
        rsp.orderBy = page.getOrderBy();
        rsp.pageNum = page.getPageNum();
        rsp.pageSize = page.getPageSize();
        rsp.pages = page.getPages();
        rsp.total = page.getTotal();
        rsp.startRow = page.getStartRow();
        return rsp;
    }
}
