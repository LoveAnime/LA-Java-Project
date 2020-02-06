package com.anime.strategy.comparator;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 11:31
 * 描述：：传入排序对象和比较器
 * 优点：可以根据不同的比较器实现不同的排序规则
 */
public class Sorter<T> {

    public void sort(T[] ds, Comparator<T> comparator) {
        for (int i = 0; i < ds.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < ds.length; j++) {
                minPos = comparator.compare(ds[j], ds[minPos]) < 0 ? j : minPos;
            }
            if (minPos != i) {
                swap(ds, i, minPos);
            }
        }
    }

    private void swap(T[] ds, int i, int j) {
        T temp = ds[i];
        ds[i] = ds[j];
        ds[j] = temp;
    }
}
