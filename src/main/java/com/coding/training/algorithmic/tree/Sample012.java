package com.coding.training.algorithmic.tree;


/**
 * https://cloud.tencent.com/developer/article/1424230
 * 题目描述
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class Sample012 {
    public TreeNode connect(TreeNode root) {
        if (root == null) return null;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null)
                root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    /**
     *  private Node findToLinkedNode(Node node) {
     *         if (node == null) return null;
     *         if (node.left != null) return node.left;
     *         if (node.right != null) return node.right;
     *         return findToLinkedNode(node.next);
     *     }
     *
     *     public Node connect(Node root) {
     *         if (root == null) return null;
     *         if (root.left != null) {
     *             if (root.right != null) {
     *                 root.left.next = root.right;
     *             } else {
     *                 root.left.next = findToLinkedNode(root.next);
     *             }
     *         }
     *
     *         if (root.right != null) {
     *             root.right.next = findToLinkedNode(root.next);
     *         }
     *
     *         connect(root.right);
     *         connect(root.left);
     *         return root;
     *     }
     */
}
