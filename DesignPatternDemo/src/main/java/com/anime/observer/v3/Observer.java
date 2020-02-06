package com.anime.observer.v3;

/**
 * @author 陌上丶天琊
 * @date 2019-10-29 10:34
 * 描述：观察者接口
 */
public interface Observer {
    void actionOnWakeUp(WakeUpEvent event);
}
