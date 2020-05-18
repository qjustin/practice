package com.coding.training.algorithmic.history.tree;

/**
 * 把排序数组转换为高度最小的二叉搜索树
 */
public class Sample009 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length <= 0) return null;
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start + end + 1) / 2 ;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);

        return root;
    }
}
