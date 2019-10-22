package com.anime.strategy.comparator;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 12:55
 * 描述： 不需要增加比较方法，或者实现接口
 */
public class Dog {
    private int hight;

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    Dog(int hight) {
        this.hight = hight;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "hight=" + hight +
                '}';
    }
}
