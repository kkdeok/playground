package com.kkd.exercise.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-bottom-left-tree-value/
 */
public class FindBottomLeftTreeValue {

    public static void main(String[] args) {
        FindBottomLeftTreeValue app = new FindBottomLeftTreeValue();
    }

    public int findBottomLeftValue(TreeNode root) {
        int height = getHeight(root);
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> h = new LinkedList<>();

        q.add(root);
        h.add(0);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            int temph = h.poll();

            if (temph == height) {
                return temp.val;
            }

            if (temp.left != null) {
                q.add(temp.left);
                h.add(temph + 1);
            }
            if (temp.right != null) {
                q.add(temp.right);
                h.add(temph + 1);
            }
        }
        return 0;
    }

    private int getHeight(TreeNode root) {
        int lh = root.left != null ? getHeight(root.left) + 1 : 0;
        int rh = root.right != null ? getHeight(root.right) + 1 : 0;
        return Math.max(lh, rh);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

