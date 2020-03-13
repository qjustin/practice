package com.coding.training.algorithmic.history.tree;

import java.util.LinkedList;

/**
 * 创建二叉树
 */
public class Sample000 {
    public TreeNode createTree(int nodeCount) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode curr = null;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode root = new TreeNode(1);
        queue.add(root);

        for (int i = 2; i < nodeCount; i += 2) {
            curr = queue.remove();
            left = new TreeNode(i);
            right = new TreeNode(i + 1);
            curr.setLeft(left);
            curr.setRight(right);
            queue.add(left);
            queue.add(right);
        }

        return root;
    }

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int left = low;
            int right = high;
            int pivot = arr[low];

            while (left < right) {
                while(left < right && arr[right] >= pivot) {
                    right--;
                }
                arr[right] = arr[left];
                while(left < right && arr[left] <= pivot) {
                    left++;
                }
                arr[left] = arr[right];
                arr[right] = pivot;
            }

            quickSort(arr, low, left - 1);
            quickSort(arr, left + 1, high);
        }
    }

    public TreeNode createBinaryTree(int[] arr, int index) {
        TreeNode treeNode = null;
        if (index < arr.length) {
            treeNode = new TreeNode(arr[index]);
            treeNode.left = createBinaryTree(arr, 2 * index + 1);
            treeNode.right = createBinaryTree(arr, 2 * index + 2);
        }

        return treeNode;
    }
}
