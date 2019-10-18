package comparator;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 陌上丶天琊
 * @date 2019-10-13 11:31
 * 描述：
 */
public class Main {
    public static void main(String[] args) {
        Dog[] ds = {new Dog(3), new Dog(1), new Dog(5)};
        Sorter<Dog> sorter = new Sorter<>();
        sorter.sort(ds, new DogComparator());
        System.out.println(Arrays.asList(ds));
    }

}
