package com.anime.observer.v3;

/**
 * @author 陌上丶天琊
 * @date 2019-10-29 10:30
 * 描述：如果需要根据小孩不同时间点醒来，采取不同动作，则需要增加事件类
 *
 * 1、事件源对象EventSource：小孩子
 * 2、事件类Event：小孩子醒来
 * 3、观察者Observer：妈妈喂食、爸爸玩等
 */
public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.addObserver(new Mum()).addObserver(new Dad());
        child.wakeUp();
    }
}
