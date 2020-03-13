package com.coding.training.algorithmic.entity;

public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
        this(0);
    }

    public TreeNode(int value) {
        this(value, null, null);
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
