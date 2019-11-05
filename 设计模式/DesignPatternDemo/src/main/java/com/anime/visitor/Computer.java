package com.anime.visitor;

/**
 * @author 陌上丶天琊
 * @date 2019-11-05 20:10
 * 描述：
 */
public class Computer {
    private ComputerPart mainboard = new Mainboard();
    private ComputerPart cpu = new Cpu();
    private ComputerPart memory = new Memory();

    public void acceptCustomer(Visitor visitor){
        cpu.acceptCustomer(visitor);
        mainboard.acceptCustomer(visitor);
        memory.acceptCustomer(visitor);
    }

}
