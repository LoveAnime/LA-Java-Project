package com.anime.strategy.comparator;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 16:39
 * 描述： 自定义Dog的排序比较器
 */
public class DogComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog o1, Dog o2) {
        return Integer.compare(o1.getHight(), o2.getHight());
    }
}
