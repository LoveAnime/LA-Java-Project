package com.anime.singleton;

/**
 * 懒汉式：synchronized
 * 在方法内部加锁，并进行双重检查
 * <p>
 * 优点：能实现严格的单例模式
 *
 * ps：必须使用volatile修饰，因为涉及到指令重排的问题。
 * Integer i =  new Integer(2)的过程实际上分三步：申请内存（赋默认值0）、赋初值(2)、指向对象(i=2)
 * 正常情况下是第一、二、三顺序执行，但指令重排之后，可能会按第一、三、二执行。
 * 这种情况下在超高并发时，可能会出现bug。
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
