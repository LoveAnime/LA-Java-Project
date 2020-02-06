package com.anime.acg.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 创建人：帝歌恋雪
 * 创建时间：2017-11-08 21:55
 * 说明：配置和数据库查询结果一一对应的实例类（PO类）
 *
 */
public class AcgModel implements Serializable {
    /**
     * 主键
     * @NotNull 注解 用来设置校验规则：主键字段不能为空
     */
    @NotNull(message="id cannot be notnull.")
    private int id;

    @NotNull(message="acgName cannot be notnull.")
    private String acgName;

    private String acgAuthor;

    private String acgCategory;

    private int episode;

    private String completed;

    /**
     * @JsonFormat 注解 校验日期格式
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String country;

    private String watchAble;

    private String memo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcgName() {
        return acgName;
    }

    public void setAcgName(String acgName) {
        this.acgName = acgName;
    }

    public String getAcgAuthor() {
        return acgAuthor;
    }

    public void setAcgAuthor(String acgAuthor) {
        this.acgAuthor = acgAuthor;
    }

    public String getAcgCategory() {
        return acgCategory;
    }

    public void setAcgCategory(String acgCategory) {
        this.acgCategory = acgCategory;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWatchAble() {
        return watchAble;
    }

    public void setWatchAble(String watchAble) {
        this.watchAble = watchAble;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "AcgModel{" +
                "id=" + id +
                ", acgName='" + acgName + '\'' +
                ", acgAuthor='" + acgAuthor + '\'' +
                ", acgCategory='" + acgCategory + '\'' +
                ", episode=" + episode +
                ", completed=" + completed +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", country='" + country + '\'' +
                ", watchAble=" + watchAble +
                ", memo='" + memo + '\'' +
                '}';
    }
}
