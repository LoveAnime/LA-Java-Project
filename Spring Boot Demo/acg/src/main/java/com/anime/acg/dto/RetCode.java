package com.anime.acg.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * 创建人：帝歌恋雪
 * 创建时间：2017-11-08 21:27
 * 说明：状态编码
 * 包括操作成功、服务器异常、身份验证失败、字段校验错误、业务模块异常等场景
 * msgId必须与和前台的状态码一一对应
 */
// todo 为什么得有这个注解
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RetCode implements Serializable{
    Success("0000","Success"),
    ServerError("0010","服务器访问异常"),
    BusinessError("0011","业务繁忙"),
    PermissionDenied("0020","拒绝访问"),
    AuthFailed("0030","身份信息验证失败"),
    ValidationError("0031","字段校验不合法")
    ;

    /**
     * 自定义属性：状态码ID编号
     */
    private final String msgId;

    /**
     * 自定义属性：状态码代码
     */
    private final String msgCode;

    /**
     * 自定义属性：本地化的提示信息
     */
    private String msg;

    // 构造函数
    RetCode(String msgId, String msgCode) {
        this.msgCode = msgCode;
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RetCode{" +
                "msgId='" + msgId + '\'' +
                ", msgCode='" + msgCode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
