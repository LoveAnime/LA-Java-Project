package com.anime.observer.v1;

/**
 * @author 陌上丶天琊
 * @date 2019-10-28 23:21
 * 场景描述：小孩子哭了，妈妈就要喂食
 * 方法1：直接将妈妈的喂食动作，加到小孩子哭的动作里
 */
public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }
}
