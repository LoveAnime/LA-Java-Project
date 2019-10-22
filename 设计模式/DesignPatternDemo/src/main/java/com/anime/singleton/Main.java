package com.anime.singleton;

/**
 * @author 陌上丶天琊
 * @date 2019-10-11 22:10
 * 描述: 单例模式分为恶汉式和懒汉式两种，可以通过静态变量，加锁，枚举三种方式来实现
 */
public class Main {
    public static void main(String[] args) {
        test3();
    }

    public static void test3() {
        for (int i = 0; i < 100; i++) {
            new Thread(
                    () -> System.out.println(Singleton7.INSTANCE.hashCode())
            ).start();
        }
    }

    public static void test2() {
        for (int i = 0; i < 100; i++) {
            new Thread(
                    () -> System.out.println(Singleton6.newInstance().hashCode())
            ).start();
        }
    }

    public static void test1() {
        Singleton2 s1 = Singleton2.newInstance();
        Singleton2 s2 = Singleton2.newInstance();
        System.out.println(s1 == s2);
    }
}
