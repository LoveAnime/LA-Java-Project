package com.anime.observer.v2;

/**
 * @author 陌上丶天琊
 * @date 2019-10-28 23:21
 * 场景描述：定义观察者接口，统一观察者行为模式
 */
public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.addObserver(new Mum());
        child.wakeUp();
    }
}
