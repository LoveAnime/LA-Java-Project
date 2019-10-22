package com.anime.factory.factoryMethod;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 15:36
 * 场景：我想选择一种交通工具出去旅行
 * 描述：简单工厂方法指的是，根据不同的交通工具直接新增相应的工厂方法
 * 如PlaneFactory.create()，或者vehicleFactory.createCar()
 */
public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new CarFactory().create();
        vehicle.run();

        Vehicle vehicle1 = new VehicleFactory().createCar();
        vehicle1.run();
    }
}
