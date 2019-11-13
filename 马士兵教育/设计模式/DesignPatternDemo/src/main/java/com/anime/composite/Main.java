package com.anime.composite;

/**
 * @author 陌上丶天琊
 * @date 2019-10-29 18:58
 * 描述：比如文件夹存储系统，一个目录可以存多个文件夹或文件
 * 每个文件夹可以继续存文件夹或文件
 */
public class Main {
    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode chapter1 = new BranchNode("chapter1");
        BranchNode chapter2 = new BranchNode("chapter2");
        BranchNode section1 = new BranchNode("section21");
        LeafNode c11 = new LeafNode("c11");
        LeafNode c12 = new LeafNode("c12");
        LeafNode c211 = new LeafNode("c211");
        LeafNode c212 = new LeafNode("c212");

        root.add(chapter1);
        root.add(chapter2);
        chapter2.add(section1);
        chapter1.add(c11);
        chapter1.add(c12);
        section1.add(c211);
        section1.add(c212);

        tree(root, 0);
    }

    public static void tree(Node root, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("-");
        }
        root.print();
        if (root instanceof BranchNode) {
            for (Node node : ((BranchNode) root).getNodes()) {
                tree(node, depth + 1);
            }
        }
    }
}
