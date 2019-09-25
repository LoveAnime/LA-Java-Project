package com.anime.model;

import com.anime.config.PageInfo;

/**
 * 创建人：帝歌恋雪
 * 创建时间：2017-11-15 20:45
 * 说明：查询类，包括查询信息和分页信息
 */
public class AcgQueryModel {
    private String keyword;
    private String acgCategory;
    private String completed;
    private String watchAble;
    private String country;
    private PageInfo pageInfo;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getWatchAble() {
        return watchAble;
    }

    public void setWatchAble(String watchAble) {
        this.watchAble = watchAble;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getAcgCategory() {
        return acgCategory;
    }

    public void setAcgCategory(String acgCategory) {
        this.acgCategory = acgCategory;
    }
}
