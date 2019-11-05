package com.anime.visitor;

/**
 * @author 陌上丶天琊
 * @date 2019-11-05 20:10
 * 描述：
 */
public class Memory extends ComputerPart {

    @Override
    public void acceptCustomer(Visitor visitor) {
        visitor.buyMemory(this);
    }

    @Override
    public int getPrice() {
        return 500;
    }
}
