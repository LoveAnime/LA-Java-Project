package com.anime.singleton;

/**
 * 懒汉式：synchronized
 * 在方法内部加锁，并进行双重检查
 * <p>
 * 优点：能实现严格的单例模式
 */
public class Singleton6 {
    private static volatile Singleton6 instance;

    private Singleton6() {
    }

    /**
     * @return 获取单例的方法
     */
    public synchronized static Singleton6 newInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    // 可以在此暂停线程测试是否可以实现真正的单例模式
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
