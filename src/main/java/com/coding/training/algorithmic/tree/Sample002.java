package com.coding.training.algorithmic.tree;

/**
 *
 * 递归 先序，中序，后续，遍历二叉树
 *
 * 递归方式实现前序遍历
 * 具体过程：
 * <p>
 * 先访问根节点
 * 再序遍历左子树
 * 最后序遍历右子树
 */

/**
 * 递归方式实现中序遍历
 * 具体过程：
 *
 * 先中序遍历左子树
 * 再访问根节点
 * 最后中序遍历右子树
 */

/**
 * 递归方式实现后序遍历
 * <p>
 * 先后序遍历左子树
 * 再后序遍历右子树
 * 最后访问根节点
 *
 *
 *前序遍历：1 2 4 5 3 6 7
 *中序遍历：4 2 5 1 6 3 7
 *后序遍历：4 5 2 6 7 3 1
 */

public class Sample002 {
    public static void main(String[] args) {
        TreeNode root = new Sample000().createTree(7);

        preOrder(root);
        System.out.println();
        midOrder(root);
        System.out.println();
        posOrder(root);
    }

    public static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.getValue() + " ");
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    public static void midOrder(TreeNode root) {
        if (root == null) return;
        midOrder(root.getLeft());
        System.out.print(root.getValue() + " ");
        midOrder(root.getRight());
    }

    public static void posOrder(TreeNode root) {
        if (root == null) return;
        posOrder(root.getLeft());
        posOrder(root.getRight());
        System.out.print(root.getValue() + " ");
    }








































    /*
    public static void preOrder(TreeNode root) {
        if (root == null) return;

        System.out.println(root.getValue());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

     public static void midOrder(TreeNode root) {
        if (root == null) return;

        midOrder(root.getLeft());
        System.out.println(root.getValue());
        midOrder(root.getRight());
    }

     public static void posOrder(TreeNode root) {
        if (root == null) return;

        posOrder(root.getLeft());
        posOrder(root.getRight());
        System.out.println(root.getValue());
    }
    */
}
