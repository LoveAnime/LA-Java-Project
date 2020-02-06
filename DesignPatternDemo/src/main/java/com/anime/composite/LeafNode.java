package com.anime.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 陌上丶天琊
 * @date 2019-10-29 19:14
 * 描述：叶子结点，即文件
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LeafNode extends Node {
    private String fileName;

    public LeafNode(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void print() {
        System.out.println(fileName);
    }
}
