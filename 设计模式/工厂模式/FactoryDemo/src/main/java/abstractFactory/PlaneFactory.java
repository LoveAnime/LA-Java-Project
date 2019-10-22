package abstractFactory;

/**
 * @author 陌上丶天琊
 * @date 2019-10-21 16:16
 * 描述：
 */
public class PlaneFactory extends AbstractVehicleFactory {

    @Override
    AbstractVehicle createVehicle() {
        return new Plane();
    }

    @Override
    AbstractWeapon createWeapon() {
        return new AK47();
    }

    @Override
    AbstractFood createFood() {
        return new Bread();
    }
}
