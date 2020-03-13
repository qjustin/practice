package com.coding.training.algorithmic.offer;

import com.coding.training.algorithmic.entity.Node;
import com.coding.training.algorithmic.entity.TreeNode;

/**
 * 面试题07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 限制：
 * 0 <= 节点个数 <= 5000
 * 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 通过次数7,761提交次数11,353
 * 思路：
 * 其前序遍历序列为：{1,2,4,7,3,5,6,8}，中序遍历序列为：{4,7,2,1,5,3,8,6}。
 * 由于在中序遍历中，有三个左子树节点的值，因此在前序遍历的序列中，根节点后面的3个数字就是3个左子树节点的值，
 * 再后面的所有数字都是右子树节点的值。这样子我们就在前序序列和中序序列中找到了左右子书对应的子序列，然后再递归处理即可。如下图所示：
 * 前序序列：
 * 1,       2,4,7,      3,5,6,8
 * root     左子树       右子树
 * 中序序列：
 * 4,7,2,       1,         5,3,8,6
 * 左子树        root      右子树
 * 理解上面的过程，即可根据前序序列和中序序列写出如下构建二叉树代码。
 * 二叉树的存储可以先用线性存储，如数组，或者链式存储，本文采用的就是链式存储。实现废话不多说，上代码：
 * 前序中序重建二叉树 
 * 具体分析
 * 前序中第一个元素总是二叉树的根节点，拿到根节点后遍历中序，寻找中序中的根节点，找到后根节点。
 * 左边的元素为根节点的左子树，右边的元素为右子树，例如上题中的前序preOrder = {1,2,4,7,3,5,6,8}和中序为inOrder =
 * {4,7,2,1,5,3,8,6} 。
 * 第一次：根节点为{1}
 * 左子树为：preOrder = {2,4,7},inOrder = {4,7,2}
 * 右子树为：preOrder = {3,5,6,8},inOrder = {5,3,8,6}
 * 第二次左子树：根节点为{2}
 * 左子树为：preOrder = {4,7}，inOrder = {4,7}
 * 右子树为：null，因为inOrder = {2,4,7}根节点元素2右边没有节点
 * 第二次右子树：根节点为{3}
 * 左子树为：{5}，一个节点
 * 右子树为：preOrder= {6,8}，inOrder = {8,6}
 * 以此类推
 * 这部分写的好像不是很明白，如果看不懂直接从代码中的重要注释看。
 * 代码中的重要注释
 * 估计看着不方便，所以把代码中的重要注释贴在这里
 * 这里很重要！！！肯定能看懂
 * 接着进行递归，因为得到中序的根节点后，中序数组根节点左边的元素为左子树，右边的元素为右子树，计算左右子树的起始和结束下标，然后继续进行递归
 * 计算左树子起始结束下标
 * 前序起始位置startPre = 原来的前序的startPre后移一位，也就是 startPre = startPre + 1。
 * 前序结束下标endPre = 前序起始位置 + 左子树元素的长度，左子树长度=查出的中序根节点下标值减去中序的初始下标，也就是左子树长度 = i - startIn,综合起来就是前序结束下标 enPre = startPre + i - startIn。
 * 中序起始位置startIn = startIn ，保持不变。
 * 前序结束下标endIn = 查出来的中序根节点下标值 - 1，即endIn = i-1。
 * 计算右子树起始结束下标
 * 前序起始位置其实就是左子树前序结束下标+1，因为总的前序被分为两部分，一部分为左子树的前序， 一部分为右子树的前序，既然左子树的前序结束下标为endPre = startPre+i-startIn,那么右子树的起始下标startIn=startPre+i-startIn+1。
 * 而右子树的结束下标也自然而然是endPre。
 * 右子树的中序起始下标startIn同理，左右子树的中序也将总的中序分为两部分，而且处于查出来的中序根节点下标值的两边，即左子树结束下标为i-1，右子树起始下标为i+1。
 * 而右子树的结束下标也自然而然是endIn。
 */
public class Num0005 {
    public static TreeNode restoreBinaryTree(int[] preOrderArr, int preStartIdx, int preEndIdx, int[] midOrderArr, int midStartIdx, int midEndIdx) {
        /**
         * 1. 从 preOrderArr中取出第一个元素 r 作为根
         * 2. 从 midOrderArr中 找到 r 的位置
         * 3. 获取左子树的长度与起始/结束下标 leftSubTreeLength 与右子树的长度与起始/结束下标 rightSubTreeLength
         */

        Node node = new Node(preOrderArr[preStartIdx]);
        int rootIndex = binarySearch(midOrderArr, node.value);
        if (rootIndex > 0) {

        }
    }

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int pivot = low + (high - low) / 2;

            if (target <= arr[pivot]) {
                high = pivot - 1;
            } else {
                low = pivot + 1;
            }
        }

        return low < arr.length && target == arr[low] ? low : -1;
    }

    public static void main(String[] args) {
        int[] preOrderArr = new int[]{};
        int[] midOrderArr = new int[]{};

        TreeNode root = restoreBinaryTree(preOrderArr, midOrderArr);
    }
}
