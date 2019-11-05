package com.anime.visitor;

/**
 * @author 陌上丶天琊
 * @date 2019-11-05 21:28
 * 描述：
 */
public interface Visitor {

    void buyCpu(ComputerPart computerPart);

    void buyMemory(ComputerPart computerPart);

    void buyMainboard(ComputerPart computerPart);
}
