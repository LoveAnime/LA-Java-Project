package com.anime.visitor;

/**
 * @author 陌上丶天琊
 * @date 2019-11-05 20:15
 * 描述：
 */
public abstract class ComputerPart {

    /**
     * 接待消费者
     *
     * @param visitor
     */
    public abstract void acceptCustomer(Visitor visitor);

    /**
     * @return 价格
     */
    public abstract int getPrice();
}
