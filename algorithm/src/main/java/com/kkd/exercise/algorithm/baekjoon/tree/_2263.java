package com.kkd.exercise.algorithm.baekjoon.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class _2263 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int n;
	private static int[] inorder;
	private static int[] postorder;

	static class Node {
		int val;
		Node left, right;
	}
	

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		process();
	}

	public static void process() {
		int is = 0, ie = inorder.length-1;
		int ps = 0, pe = postorder.length-1;
		Node root = makeTree(is, ie, ps, pe);
		preorder(root);
	}

	private static Node makeTree(int is, int ie, int ps, int pe) {
		if (ie < is || pe < ps) return null;
		
		Node node = new Node();
		node.val = postorder[pe];
		node.left = node.right = null;
		
		if (ps != pe) {
			int idx=is;
			for (; idx<=ie ; idx++) {
				if (inorder[idx] == node.val)
					break;
			}
			node.left = makeTree(is, idx-1, ps, ps + (idx - is) - 1);
			node.right = makeTree(idx+1, ie, ps + (idx - is), pe-1);
		}
		return node;
	}

	private static void preorder(Node root) {
		Stack<Node> s = new Stack<>();
		s.push(root);
		StringBuilder sb = new StringBuilder();
		while (!s.isEmpty()) {
			Node node = s.pop();
			sb.append(node.val + " ");
			if (node.right != null) s.push(node.right);
			if (node.left != null) s.push(node.left);
		}
		System.out.println(sb.toString());
	}

}
