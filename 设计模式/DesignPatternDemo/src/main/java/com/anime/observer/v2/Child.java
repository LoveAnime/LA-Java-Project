package com.anime.observer.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陌上丶天琊
 * @date 2019-10-28 23:20
 * 优点：addObserver()可以用于添加不同的观察者，只需要实现observer接口
 * 其中，addObserver()常与责任链模式结合使用
 *
 */
public class Child {
    private boolean cryFlag = false;
    private List<Observer> list = new ArrayList<>();

    public void addObserver(Observer observer){
        list.add(observer);
    }

    public void wakeUp() {
        System.out.println("baby wakeup, baby cry...");
        cryFlag = true;
        for (Observer o : list) {
            o.actionOnWakeUp();
        }
    }
}
