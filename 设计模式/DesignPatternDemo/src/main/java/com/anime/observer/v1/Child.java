package com.anime.observer.v1;

/**
 * @author 陌上丶天琊
 * @date 2019-10-28 23:20
 * 方法：直接将妈妈的喂食动作，加到小孩子哭的动作里
 * 缺点：观察者和被观察者的代码耦合在一起，不利于扩展，特别是不同观察者的行为不一致时
 */
public class Child {
    private boolean cryFlag = false;
    private Mum mum = new Mum();

    public void wakeUp() {
        System.out.println("baby wakeup, baby cry...");
        cryFlag = true;
        mum.feed();
    }
}
