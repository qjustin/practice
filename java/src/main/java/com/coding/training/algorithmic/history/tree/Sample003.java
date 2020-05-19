package com.coding.training.algorithmic.history.tree;

/**
 * https://blog.csdn.net/QiuYang18/article/details/58245626
 * <p>
 * 普通的二叉树求两个节点的最低公共祖先
 * <p>
 * 解题思路一：
 * <p>
 * 如果一个结点为根，另一个结点无论在什么地方它们的最低公共祖先一定为根结点。
 * <p>
 * 如果一个结点在左树，另一个结点在右树，那么它的最低公共祖先一定是根节点。
 * <p>
 * 如果两个结点都在左树，以子问题在左树查找。
 * <p>
 * 如果两个结点都在右树，以子问题在右树查找。
 * <p>
 * 形式一：当树为二叉排序树，如何寻找给定两节点的最低公共祖先?
 * <p>
 * 形式二：当树为普通树，但每个节点中有指针指向其父节点，如何寻找?
 * <p>
 * 形式三：当树为二叉树，每个节点仅有左右孩子指针，如何寻找?
 * <p>
 * 形式四：当树为普通树，每个节点仅有左右孩子指针，如何寻找?
 *
 *
 * * 1.二叉搜索树具有一个很好的特点。以当前结点为根节点的左边结点的值都是小于根节点的值，右边结点的值都大于根节点的值。
 *  * 2.根据这个特点，如果给的两个节点的值都小于根节点，那么它们的最低公共祖先就一定在它左子树。
 *  * 3.如果给的两个节点的值都大于根节点，那么它们的最低公共祖先就一定在它右子树。
 *  * 4.如果一个结点的值大于根节点的值，一个结点的值小于根节点的值，那么这个根节点就是它的最低公共祖先。
 *  *
 */
public class Sample003 {
    public static void main(String[] args) {

    }

    //通过遍历二叉搜索树即可完成查找
    /*
    inOrder: 1 2 3 4 5 6 7 9
    parent:3
     */
    private static TreeNode findParent(TreeNode root, TreeNode node_1, TreeNode node_2) {

        if (root == null)
            return null;
        if (root.getValue() > node_1.getValue() && root.getValue() > node_2.getValue())
            return findParent(root.getLeft(), node_1, node_2);
        else if (root.getValue() < node_1.getValue() && root.getValue() < node_2.getValue())
            return findParent(root.getRight(), node_1, node_2);
        else
            return root;   //用此条件判断两数之间也可(root.data-node_1.data)*(root.data-node_2.data)<0
    }

    /**
     * 解题思路
     * 解题思路
     * 后序遍历二叉树 假设遍历到的当前结点为cur 先处理左子树和右子树 left和right
     * 如果cur为null 或者为p和q中的任意一个 直接返回cur
     * 如果left和right都不为null 返回cur
     * 如果left不为null 返回left
     * 如果right不为null 返回right
     */

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode left, TreeNode right) {
        // root,left,right中有一个为null,返回null
        // root == left代表从left中查left,right的最低祖先，此时最低祖先应该为left（即left是right的祖先）
        if (root == null || root == left || root == right) {
            return root;
        }

        // 如果left, right不存在一个是另一个的祖先关系
        TreeNode leftNode = findParent(root.getLeft(), left, right); //从左子树去查并返回祖先指针
        TreeNode rightNode = findParent(root.getRight(), left, right); //从右子树去查并返回祖先指针

        //l,r都不为空，说明left,right分布在当前节点的左右子树当中，则当前节点为最低公共祖先
        if (leftNode != null && rightNode != null) {
            return root;
        }

        // //如果l不为空，r为空，说明left,right都在左子树当中，且l为
        return leftNode != null ? leftNode : rightNode;
    }

}
