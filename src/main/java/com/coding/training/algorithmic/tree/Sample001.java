package com.coding.training.algorithmic.tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 非递归 先序，中序，后续，层次，遍历二叉树
 *
 * https://www.cnblogs.com/songwenjie/p/8955856.html
 * <p>
 * 非递归方式实现前序遍历
 * <p>
 * 先序：拿到一个节点后，即刻输出该节点的值，并继续遍历其左右子树。(根左右)
 * <p>
 * 具体过程：
 * <p>
 * 1. 首先申请一个新的栈，记为stack；
 * 2. 将头结点head压入stack中；
 * 3. 每次从stack中弹出栈顶节点，记为cur，然后打印cur值，
 * 如果cur右孩子不为空，则将右孩子压入栈中；
 * 如果cur的左孩子不为空，将其压入stack中；
 * 4. 重复步骤3，直到stack为空.
 * <p>
 * 非递归方式实现中序遍历
 * <p>
 * 中序：拿到一个节点后，将其暂存，遍历完左子树后，再输出该节点的值，然后遍历右子树。(左根右)
 * <p>
 * 具体过程：
 * <p>
 * 1 申请一个新栈，记为stack，申请一个变量cur，初始时令cur为头节点；
 * 2 先把cur节点压入栈中，对以cur节点为头的整棵子树来说，依次把整棵树的左子树压入栈中，
 * 即不断令cur=cur.left，然后重复步骤2；
 * 3 不断重复步骤2，直到发现cur为空，此时从stack中弹出一个节点记为node，打印node的值，
 * 并让cur = node.right，然后继续重复步骤2；
 * 4 当stack为空并且cur为空时结束。
 *
 * <p>
 * 非递归方式实现后序遍历
 * 具体过程：
 * <p>
 * 后序：拿到一个节点后，将其暂存，遍历完左右子树后，再输出该节点的值。(左右根)
 * <p>
 * 使用两个栈实现
 * <p>
 * 1 申请两个栈stack1，stack2，然后将头结点压入stack1中；
 * 2 从stack1中弹出的节点记为cur，然后先把cur的左孩子压入stack1中，再把cur的右孩子压入stack1中；
 * 3 在整个过程中，每一个从stack1中弹出的节点都放在第二个栈stack2中；
 * 4 不断重复步骤2和步骤3，直到stack1为空，过程停止；
 * 5 从stack2中依次弹出节点并打印，打印的顺序就是后序遍历的顺序；
 * <p>
 * 层序遍历
 * 每次从左到右遍历完一层，再进入下一层
 * <p>
 * 具体过程：
 * <p>
 * 首先申请一个新的队列，记为queue；
 * 将头结点head压入queue中；
 * 每次从queue中出队，记为node，然后打印node值，如果node左孩子不为空，则将左孩子入队；如果node的右孩子不为空，则将右孩子入队；
 * 重复步骤3，直到queue为空。
 * <p>
 * <p>
 * 前序遍历：1 2 4 5 3 6 7
 * 中序遍历：4 2 5 1 6 3 7
 * 后序遍历：4 5 2 6 7 3 1
 * 层次遍历：1 2 3 4 5 6 7
 */
public class Sample001 {
    public static void main(String[] args) {
        TreeNode root = new Sample000().createTree(7);

        System.out.println();
        preOrder(root);
        System.out.println();
        midOrder(root);
        System.out.println();
        posOrder(root);
        System.out.println();
        levelOrder(root);
        System.out.println();
    }

    public static void preOrder(TreeNode node) {
        if (node == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        TreeNode curr;

        while (!stack.empty()) {
            curr = stack.pop();
            System.out.print(curr.getValue() + " ");

            if (curr.getRight() != null) {
                stack.push(curr.getRight());
            }

            if (curr.getLeft() != null) {
                stack.push(curr.getLeft());
            }
        }
    }

    public static void posOrder(TreeNode node) {
        if (node == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        TreeNode current;
        TreeNode visited = null;

        while (!stack.empty()) {
            current = stack.peek();

            if (current.getLeft() != null && current.getLeft() != visited && current.getRight() != visited) {
                stack.push(current.getLeft());
            } else if (current.getRight() != null && current.getRight() != visited) {
                stack.push(current.getRight());
            } else {
                System.out.print(stack.pop().getValue() + " ");
                visited = current;
            }
        }
    }

    public static void midOrder(TreeNode node) {
        if (node == null) return;

        TreeNode curr = node;
        Stack<TreeNode> stack = new Stack<>();

        while (curr != null || !stack.empty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }

            curr = stack.pop();
            System.out.print(curr.getValue() + " ");
            curr = curr.getRight();
        }
    }

    public static void levelOrder(TreeNode node) {
        if (node == null) return;

        TreeNode curr;
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(node);
        while (!queue.isEmpty()) {
            curr = queue.poll();
            System.out.print(curr.getValue() + " ");

            if (curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }

            if (curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
    }


























































/*

    public static void preOrder(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            System.out.print(curr.getValue() + ",");

            if (curr.getRight() != null) {
                stack.push(curr.getRight());
            }

            if (curr.getLeft() != null) {
                stack.push(curr.getLeft());
            }
        }
    }

    private static void midOrder(TreeNode root) {
        if (root == null) return;

        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();

        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }

            curr = stack.pop();
            System.out.print("," + curr.getValue());
            curr = curr.getRight();
        }
    }

    public static void posOrder(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode visited = null;
        TreeNode current = null;

        while(!stack.empty()) {
            current = stack.peek();

            if (current.getLeft() != null && current.getLeft() != visited && current.getRight() != visited) {
                stack.push(current.getLeft());
            } else if (current.getRight() != null && current.getRight() != visited) {
                stack.push(current.getRight());
            } else {
                System.out.print(stack.pop().getValue() + " ");
                visited = current;
            }
        }
    }
    */
}


