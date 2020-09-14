package com.kkd.study.problem_solving.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/path-sum-ii/
 */
public class PathSumII {
    
    public static void main(String[] args) {
        
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();

        if (root == null) {
            return ret;
        }

        Queue<TreeNode> q = new LinkedList<>();
        Queue<List<Integer>> l = new LinkedList<>();
        q.add(root);
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        l.add(list);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            List<Integer> tempList = l.poll();

            // leaf node
            if (temp.left == null && temp.right == null) {
                int tempSum = tempList.stream().mapToInt(i -> i).sum();
                if (tempSum == sum) {
                    ret.add(tempList);
                }
            } else {
                if (temp.left != null) {
                    q.add(temp.left);
                    List<Integer> tempListNew = new ArrayList<>(tempList);
                    tempListNew.add(temp.left.val);
                    l.add(tempListNew);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                    List<Integer> tempListNew = new ArrayList<>(tempList);
                    tempListNew.add(temp.right.val);
                    l.add(tempListNew);
                }
            }
        }

        return ret;
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


