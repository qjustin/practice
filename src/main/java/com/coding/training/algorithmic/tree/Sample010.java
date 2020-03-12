package com.coding.training.algorithmic.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中第K小的元素
 */
public class Sample010 {
    List<Integer> list = new ArrayList();

    public void dfs(TreeNode root) {
        if (root == null)
            return;
        dfs(root.left);
        list.add(root.getValue());
        dfs(root.right);
    }

    public int kthSmallest(TreeNode root, int k) {
        dfs(root);
        for (int i = 0; i < list.size(); i++) {
            if (i == k - 1)
                return list.get(i);
        }
        return -1;
    }
}
