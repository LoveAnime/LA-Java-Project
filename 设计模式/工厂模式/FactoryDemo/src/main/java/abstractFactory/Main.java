package abstractFactory;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 15:36
 * 场景：我除了想选择一种交通工具出去旅行之外，还想要带点吃的零食和防身工具
 * 描述：抽象工厂方法指的是，将基类定义为抽象类
 * 常用于需要定义产品组的场景中，比如坐飞机默认只能拿ak47吃面包，做火车只能拿电棍吃方便面
 * 比如：主题更换
 */
public class Main {
    public static void main(String[] args) {
        AbstractVehicleFactory factory = new PlaneFactory();
        factory.createVehicle().run();
        factory.createFood().eat();
        factory.createWeapon().defend();
    }
}
