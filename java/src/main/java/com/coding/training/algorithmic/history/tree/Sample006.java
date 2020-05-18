package com.coding.training.algorithmic.history.tree;

/**
 * 二叉树的最大深度
 * <p>
 * <p>
 * <p>
 * 二叉树的最小深度：二叉树的最小深度为根节点到最近叶子节点的距离。
 * 思路：
 * 一种就是计算左子树和右子树深度的时候，判断是否等于0，如果等于0，说明该子树不存在，深度赋值为最大值。
 * 第二种就是判断左子树或右子树是否为空，若左子树为空，则返回右子树的深度，反之返回左子树的深度，如果都不为空，则返回左子树和右子树深度的最小值。
 */
public class Sample006 {
    public static void main(String[] args) {

    }

    public int binaryTreeMaxDepth(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = binaryTreeMaxDepth(root.getLeft()) + 1;
        int rightDepth = binaryTreeMaxDepth(root.getRight()) + 1;

        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }


    public int binaryTreeMinDepth(TreeNode root) {
        if (root == null) return 0;

        //判断左子树或右子树是否为空
        //若左子树为空，则返回右子树的深度，反之返回左子树的深度
        if (root.getLeft() == null)
            return binaryTreeMinDepth(root.getRight()) + 1;
        if (root.getRight() == null)
            return binaryTreeMinDepth(root.getRight()) + 1;

        //如果都不为空，则返回左子树和右子树深度的最小值
        int leftDepth = binaryTreeMinDepth(root.getLeft()) + 1;
        int rightDepth = binaryTreeMinDepth(root.getRight()) + 1;

        return leftDepth < rightDepth ? leftDepth : rightDepth;
    }
}
