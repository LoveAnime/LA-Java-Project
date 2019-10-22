package com.anime.factory.factoryMethod;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 15:35
 * 描述：
 */
public class CarFactory {
    public Vehicle create(){
        return new Car();
    }
}
