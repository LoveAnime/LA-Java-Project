package com.anime.proxy.v1;

/**
 * 静态代理：将被代理对象直接添加到代理类中，并添加代理行为（执行被代理对象的方法）
 *
 * @author 陌上丶天琊
 * @date 2019-10-30 13:10
 * 场景：想要获得某个方法的执行时间，比如坦克的移动时间
 * <p>
 * 最简单的处理是直接在Tank.move()里增加方法调用时间和返回时间。
 * 但是这样做，一方面不利于扩展，比如需要计算第二个方法的执行时间，也需要修改代码。
 * 另一方面比如做性能测试时，不允许修改源码。
 * <p>
 * 静态代理的实现类似聚合模式、装饰模式等。
 * <p>
 * 说到底，大部分设计模式都是在不同场景下对多态特性的应用，语法上类似，语义不同而已。
 */
public class Main {
    public static void main(String[] args) {
        TankProxy tank = new TankProxy(new Tank());
        tank.move();
    }
}
