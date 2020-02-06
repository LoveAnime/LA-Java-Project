package com.anime.visitor;

/**
 * 访问者模式
 *
 * @author 陌上丶天琊
 * @date 2019-11-05 20:21
 * 描述：组装一台电脑，每台电脑都是由一个cpu、一个内存、一个主板组成，但是个人与企业购买电脑时享受折扣不一样
 * <p>
 * 电脑组成成分改变时，需要同时更改Computer、ComputerPart、Customer，不方便，也因此不适合访问者模式
 * 而增加一种用户时，只需要新增一个类，并实现Customer接口。
 * <p>
 * 适用场景：电脑的组成成分基本上不会变，而消费者组装电脑时享有的折扣可以任意配置扩展的场景
 */
public class Main {
    public static void main(String[] args) {
        PersonVisitor me = new PersonVisitor();
        Computer meComputer = new Computer();
        meComputer.acceptCustomer(me);
        System.out.println(me.getTotalPrice());

        CompanyVisitor boss = new CompanyVisitor();
        Computer bossComputer = new Computer();
        bossComputer.acceptCustomer(boss);
        System.out.println(boss.getTotalPrice());

        System.out.println(System.getProperties().get("user.dir"));
    }
}
