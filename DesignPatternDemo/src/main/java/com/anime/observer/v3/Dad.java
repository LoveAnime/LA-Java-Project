package com.anime.observer.v3;

import java.time.LocalTime;

/**
 * @author 陌上丶天琊
 * @date 2019-10-29 10:38
 * 描述：Mum继承观察者接口，喂食动作写在统一接口里
 */
public class Dad implements Observer {

    public void play() {
        System.out.println("Dad play with child..");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        if (event.getTime().toLocalTime().isBefore(LocalTime.of(18, 0))) {
            play();
        }
    }
}
