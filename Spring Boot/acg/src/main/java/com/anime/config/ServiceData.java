package com.anime.config;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 创建人：帝歌恋雪
 * 创建时间：2017-11-08 21:45
 * 说明：实现序列化接口，用于和前端进行交互
 * 返回属性包括：状态编码、返回的数据、附加信息
 */
public class ServiceData implements Serializable{
    /**
     * 状态编码
     */
    RetCode code = RetCode.Success;

    /**
     * 业务层操作后得到的数据对象
     */
    Object bo = null;

    /**
     * 其他想要返回给前端的附加信息
     */
    String other = null;

    public RetCode getCode() {
        return code;
    }

    /**
     * 设置状态码
     * @param request http请求
     * @param code
     */
    public void setCode(HttpServletRequest request,RetCode code) {
        this.code = code;
    }

    public Object getBo() {
        return bo;
    }

    public void setBo(Object bo) {
        this.bo = bo;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
