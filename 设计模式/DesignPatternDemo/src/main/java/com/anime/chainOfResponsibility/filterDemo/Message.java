package com.anime.chainOfResponsibility.filterDemo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 19:48
 * 描述：前端传过来的消息体
 */
@Data
public class Message {
    private String name;
    private String memo;
    private LocalDateTime updateTime;

}
