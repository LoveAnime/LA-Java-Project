package com.anime.factory.abstractFactory;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 15:40
 * 描述：
 */
public abstract class AbstractVehicleFactory {
    abstract AbstractVehicle createVehicle();
    abstract AbstractWeapon createWeapon();
    abstract AbstractFood createFood();
}
