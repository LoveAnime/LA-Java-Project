package com.anime.observer.v3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陌上丶天琊
 * @date 2019-10-29 10:38
 * 优点：addObserver()可以用于添加不同的观察者，只需要实现observer接口
 * 其中，addObserver()常与责任链模式结合使用
 */
public class Child {
    private WakeUpEvent event = new WakeUpEvent();
    private List<Observer> list = new ArrayList<>();

    public Child addObserver(Observer observer) {
        list.add(observer);
        return this;
    }

    public void wakeUp() {
        System.out.println("baby wakeup, baby cry...");
        event.setTime(LocalDateTime.now());
        for (Observer o : list) {
            o.actionOnWakeUp(event);
        }
    }
}
