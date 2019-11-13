package com.anime.strategy.comparable2;

import java.lang.*;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 12:55
 * 描述： 想要排序的对象实现Comparable接口，并重写比较方法
 * <p>
 * 缺点：实现Comparable接口进行比较的弊端是，比较方法已经在编译后确定，不能再运行时动态修改，
 * 想要更改比较方式只能更改代码，而且有且只能有一种比较方式
 */
public class Dog implements Comparable<Dog> {
    private int hight;

    Dog(int hight) {
        this.hight = hight;
    }

    @Override
    public int compareTo(Dog d) {
        return Integer.compare(this.hight, d.hight);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "hight=" + hight +
                '}';
    }
}
