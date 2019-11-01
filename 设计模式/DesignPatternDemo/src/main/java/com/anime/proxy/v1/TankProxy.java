package com.anime.proxy.v1;

/**
 * @author 陌上丶天琊
 * @date 2019-10-30 13:27
 * 描述：组合模式
 */
public class TankProxy implements Movable {
    private Movable tank;

    public TankProxy(Movable tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        tank.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
