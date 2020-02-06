package com.anime.proxy.v2;

import java.lang.reflect.Proxy;

/**
 * @author 陌上丶天琊
 * @date 2019-10-30 13:10
 * 描述：动态代理之JDK代理
 * <p>
 * 不需要显式地执行代理行为，因为movable是由JDK代理生成，
 * 调用movable.move()，实际上调用的是LogHandler.invoke()
 * <p>
 * JDK动态代理：基于接口、反射实现
 */
public class Main {
    public static void main(String[] args) {
        Tank tank = new Tank();

        // 将代理生成的类保存到文件中
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        Movable movable = (Movable) Proxy.newProxyInstance(
                Tank.class.getClassLoader(),
                new Class[]{Movable.class},
                new LogHandler<>(tank)
        );
        movable.move();
    }
}
