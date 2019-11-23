package com.anime.acg.dto;

import java.io.Serializable;

/**
 * 创建人：帝歌恋雪
 * 创建时间：2017-11-08 20:35
 * 说明：分页信息
 */
public class PageInfo implements Serializable {

    /**
     * 当前页
     */
    private int pageNow = 1;

    /**
     * 总页数
     */
    private int pageCount;

    /**
     * 当前页第一条记录的索引值
     */
    private int recordStart = 0;

    /**
     * 每页的记录数
     */
    private int pageSize = 40;

    /**
     * 总记录数
     */
    private int recordCount;

    /**
     * 上一页
     */
    private int pagePrev;

    /**
     * 下一页
     */
    private int pageNext;

    /**
     * 排序字段
     */
    private String sortField = "ID";

    /**
     * 排序方式：desc降序，asc升序
     */
    private String sortOrder = "ASC";

    /**
     * 查询关键字
     */
    private String keyword;

    /**
     * 初始化PageBean
     */
    public PageInfo() {
    }

    /**
     * PageBean构造器重载
     * @param rows 查询结果的总记录数
     */
    public PageInfo(int rows) {
        this.setRecordCount(rows);
        // 质疑：此处模板为this.pageCount = (rows-1) / this.pageSize + 1;
        this.pageCount = rows / this.pageSize + 1;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRecordStart() {
        return recordStart;
    }

    public void setRecordStart(int recordStart) {
        this.recordStart = recordStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPagePrev() {
        return pagePrev;
    }

    public void setPagePrev(int pagePrev) {
        this.pagePrev = pagePrev;
    }

    public int getPageNext() {
        return pageNext;
    }

    public void setPageNext(int pageNext) {
        this.pageNext = pageNext;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyWord) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNow=" + pageNow +
                ", pageCount=" + pageCount +
                ", recordStart=" + recordStart +
                ", pageSize=" + pageSize +
                ", recordCount=" + recordCount +
                ", pagePrev=" + pagePrev +
                ", pageNext=" + pageNext +
                ", sortField='" + sortField + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
