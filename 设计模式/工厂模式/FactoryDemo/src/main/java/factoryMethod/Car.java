package factoryMethod;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 15:09
 * 描述:
 */
public class Car implements Vehicle{
    @Override
    public void run() {
        System.out.println("四个轮子跑.");
    }
}
