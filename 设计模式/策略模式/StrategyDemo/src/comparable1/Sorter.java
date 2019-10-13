package comparable1;

/**
 * author 帝歌恋雪
 * date 2019-10-13 11:31
 * description：最基础的排序方式，直接传入特定类型的对象数组进行排序
 * 缺点：一旦更改比较对象的类型，需要重新写排序方法sort()
 */
public class Sorter {

    public static void sort(Dog[] ds) {
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

    private static void swap(Dog[] ds, int i, int j) {
        Dog temp = ds[i];
        ds[i] = ds[j];
        ds[j] = temp;
    }
}
