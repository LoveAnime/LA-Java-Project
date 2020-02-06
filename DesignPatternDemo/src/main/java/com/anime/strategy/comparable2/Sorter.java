package com.anime.strategy.comparable2;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 11:31
 * 描述：：通过实现Comparable接口进行排序，
 * 优点：只要都是Comparable的实现类，则排序方法sort()可以通用
 */
public class Sorter {

    public static void sort(Comparable[] ds) {
        for (int i = 0; i < ds.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < ds.length; j++) {
                minPos = ds[j].compareTo(ds[minPos]) < 0 ? j : minPos;
            }
            if (minPos != i) {
                swap(ds, i, minPos);
            }
        }
    }

    private static void swap(Comparable[] ds, int i, int j) {
        Comparable temp = ds[i];
        ds[i] = ds[j];
        ds[j] = temp;
    }
}
