package com.anime.factory.factoryMethod;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 15:40
 * 描述：
 */
public class VehicleFactory {
    public Vehicle createCar() {
        return new Car();
    }
}
