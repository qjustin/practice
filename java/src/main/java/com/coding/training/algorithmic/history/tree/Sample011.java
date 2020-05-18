package com.coding.training.algorithmic.history.tree;

/**
 * 从前序与中序遍历序列构造二叉树
 * 思路
 * 先序序列的第一个节点是根节点，凭此去遍历中序序列，得到中序遍历根节点的位置，
 * 于是可以从中序遍历得出左右子树的结点数，再以同样的方式去递归求出左子树结点和右子树结点
 */
public class Sample011 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return build(preorder, inorder, 0, 0, inorder.length - 1);

    }

    public TreeNode build(int[] pre, int[] in, int preL, int inL, int inR) {
        // if(inL == inR) return new TreeNode(pre[preL]);
        if (inL > inR) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preL]);
        int k = 0;
        for (int i = inL; i <= inR; ++i) {
            if (in[i] == pre[preL]) {
                k = i;
                break;
            }
        }

        int numLeft = k - inL;

        root.left = build(pre, in, preL + 1, inL, k - 1);
        root.right = build(pre, in, preL + numLeft + 1, k + 1, inR);
        return root;
    }
}
