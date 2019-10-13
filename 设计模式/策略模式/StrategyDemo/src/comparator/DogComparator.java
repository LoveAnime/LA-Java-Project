package comparator;

/**
 * author 帝歌恋雪
 * date 2019-10-13 16:39
 * description 自定义Dog的排序比较器
 */
public class DogComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog o1, Dog o2) {
        if (o1.getHight() > o2.getHight()) {
            return 1;
        } else if (o1.getHight() < o2.getHight()) {
            return -1;
        } else {
            return 0;
        }
    }
}
