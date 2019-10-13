package comparable1;

/**
 * author 帝歌恋雪
 * date 2019-10-13 12:55
 * description 排序对象，需要定义比较方法
 */
public class Dog {
    private int hight;

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public Dog(int hight) {
        this.hight = hight;
    }

    public int compareTo(Dog d) {
        if (this.hight > d.hight) {
            return 1;
        } else if (this.hight < d.hight) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
                "hight=" + hight +
                '}';
    }
}
