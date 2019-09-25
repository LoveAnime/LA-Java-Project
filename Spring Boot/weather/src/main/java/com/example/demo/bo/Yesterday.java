package com.example.demo.bo;/**
 * Created by 帝歌恋雪 on 2018-09-23.
 */

import java.io.Serializable;

/**
 * author 帝歌恋雪
 * date 2018-09-23
 * 描述: 昨日天气类
 */
public class Yesterday implements Serializable {
    private static final long serialVersionUID = 1L;
    private String date;
    private String high;
    private String fengXiang;
    private String low;
    private String fengLi;
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getFengXiang() {
        return fengXiang;
    }

    public void setFengXiang(String fengXiang) {
        this.fengXiang = fengXiang;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFengLi() {
        return fengLi;
    }

    public void setFengLi(String fengLi) {
        this.fengLi = fengLi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
