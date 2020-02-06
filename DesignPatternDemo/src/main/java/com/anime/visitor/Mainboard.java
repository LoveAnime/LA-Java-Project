package com.anime.visitor;

/**
 * @author 陌上丶天琊
 * @date 2019-11-05 20:13
 * 描述：
 */
public class Mainboard extends ComputerPart {

    @Override
    public void acceptCustomer(Visitor visitor) {
        visitor.buyMainboard(this);
    }

    @Override
    public int getPrice() {
        return 100;
    }
}
