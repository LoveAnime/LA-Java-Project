package com.anime.proxy.v1;

import java.util.Random;

/**
 * @author 陌上丶天琊
 * @date 2019-10-30 13:11
 * 描述：
 */
public class Tank implements Movable {
    @Override
    public void move() {
        System.out.print("tank move...");
        try {
            // 利用线程暂停模拟坦克移动时间
            Thread.sleep(new Random().nextInt(10000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
