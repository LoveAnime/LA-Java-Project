package com.anime.singleton;

/**
 * 懒汉式
 * 静态内部类
 * <p>
 * 优点：按需初始化，并且可以保证严格单例，是1~6中最完美的实现方式！
 * <p>
 * ps：
 * 1、类加载到内存中，不会加载静态内部类
 */
public class Singleton2 {

    private Singleton2() {
    }

    private static class InnerClass {
        private static final Singleton2 instance = new Singleton2();
    }

    /**
     * @return 获取单例的方法
     */
    public static Singleton2 newInstance() {
        return InnerClass.instance;
    }
}
