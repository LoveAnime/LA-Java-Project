package com.anime.strategy.comparator;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 14:33
 * 描述： Comparator接口，作用类似java.util.Comparator
 */
public interface Comparator<T> {
    int compare(T o1, T o2);
}
