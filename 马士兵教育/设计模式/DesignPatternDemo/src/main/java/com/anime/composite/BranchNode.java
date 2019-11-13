package com.anime.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陌上丶天琊
 * @date 2019-10-29 19:14
 * 描述：枝结点，即文件夹，文件夹下面还有子文件夹或文件
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BranchNode extends Node {
    private String pathName;
    private List<Node> nodes = new ArrayList<>();

    public BranchNode(String pathName) {
        this.pathName = pathName;
    }

    public void add(Node node) {
        nodes.add(node);
    }

    @Override
    public void print() {
        System.out.println(pathName);
    }
}
