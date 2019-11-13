package com.anime.observer.v3;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 陌上丶天琊
 * @date 2019-10-29 10:34
 * 描述：小孩子的事件类
 */
@Data
public class WakeUpEvent {
    private int type;
    private LocalDateTime time;
    // 一般来说，事件类有个可以获取事件源对象的属性
    private Object source;
}
