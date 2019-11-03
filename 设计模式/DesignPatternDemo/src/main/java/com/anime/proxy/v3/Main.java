package com.anime.proxy.v3;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author 陌上丶天琊
 * @date 2019-11-02 11:37
 * 描述：动态代理之cglib代理
 *
 * cglib基于子类、拦截器实现
 */
public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tank.class);
        enhancer.setCallback(new LogInterceptor());
        // 生成的动态代理类是tank的子类
        Tank tank = (Tank) enhancer.create();
        tank.move();
    }
}
