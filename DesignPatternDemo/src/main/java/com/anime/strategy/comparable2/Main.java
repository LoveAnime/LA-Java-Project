package com.anime.strategy.comparable2;

import java.util.Arrays;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 11:31
 * 描述：
 */
public class Main {
    public static void main(String[] args) {
        Dog[] ds = {new Dog(3), new Dog(1), new Dog(5)};
        Sorter.sort(ds);
        System.out.println(Arrays.asList(ds));
    }

}
