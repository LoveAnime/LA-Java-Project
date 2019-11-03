package com.anime.proxy.v2;

import java.util.Random;

/**
 * @author 陌上丶天琊
 * @date 2019-11-02 10:11
 * 描述：
 */
public class Tank implements Movable {
    @Override
    public void move() {
        System.out.println("tank move...");
        try {
            // 利用线程暂停模拟坦克移动时间
            Thread.sleep(new Random().nextInt(10000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
