package com.anime.factory;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 11:31
 * 描述: 工厂方法：分为工厂方法和抽象方法
 * <p>
 * 定义：任何可以产生对象的方法或类都可以称为工厂，单例也是一种工厂
 * <p>
 * 疑问：有了new，为什么还需要工厂方法呢？
 * 为了灵活控制一个对象的生产过程，包括权限、修饰、日志等。
 * <p>
 * 工厂方法和抽象方法的区别：主要在于可扩展性的维度不同
 * 工厂方法：扩展交通工具的方法比较方便，比如除了要run()，还要sleep()
 * 抽象方法：扩展某个产品组比较方便，比如除了要plane，还要car
 * <p>
 * ps：接口和抽象主要是根据语义来定义的，语法上区别不大。
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
