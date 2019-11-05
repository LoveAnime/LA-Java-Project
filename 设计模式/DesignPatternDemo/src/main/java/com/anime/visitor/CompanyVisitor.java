package com.anime.visitor;

import lombok.Data;

/**
 * @author 陌上丶天琊
 * @date 2019-11-05 21:36
 * 描述：
 */
@Data
public class CompanyVisitor implements Visitor {

    private double totalPrice = 0.0;

    private final double discount = 0.7;

    @Override
    public void buyCpu(ComputerPart computerPart) {
        totalPrice += computerPart.getPrice() * discount;
    }

    @Override
    public void buyMemory(ComputerPart computerPart) {
        totalPrice += computerPart.getPrice() * discount;
    }

    @Override
    public void buyMainboard(ComputerPart computerPart) {
        totalPrice += computerPart.getPrice() * discount;
    }
}
