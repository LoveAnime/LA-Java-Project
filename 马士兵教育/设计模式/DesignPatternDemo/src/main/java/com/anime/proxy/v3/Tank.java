package com.anime.proxy.v3;

import java.util.Random;

/**
 * @author 陌上丶天琊
 * @date 2019-11-02 11:37
 * 描述：不需要Movable接口
 */
public class Tank {
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
