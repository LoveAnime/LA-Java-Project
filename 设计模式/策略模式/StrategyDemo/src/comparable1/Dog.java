package comparable1;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 12:55
 * 描述： 排序对象，需要定义比较方法
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
        return Integer.compare(this.hight, d.hight);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "hight=" + hight +
                '}';
    }
}
