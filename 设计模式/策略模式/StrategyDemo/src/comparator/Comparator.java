package comparator;

/**
 * @author 帝歌恋雪
 * @date 2019-10-13 14:33
 * description Comparator接口，作用类似java.util.Comparator
 */
public interface Comparator<T> {
    int compare(T o1, T o2);
}
