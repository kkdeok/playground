package com.kkd.exercise.algorithm.baekjoon.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/1991
 */
public class _1991 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Node {
		char val;
		Node left;
		Node right;
	}

	static class Pair {
		char leftVal;
		char rightVal;
	}

	private static Pair[] tree;

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		tree = new Pair[26];
		for (int i = 0; i < n; i++) {
			char[] chars = br.readLine().toCharArray();
			char a = chars[0];
			char b = chars[2];
			char c = chars[4];
			tree[a - 'A'] = new Pair();
			tree[a - 'A'].leftVal = b;
			tree[a - 'A'].rightVal = c;
		}

		Node root = null;
		root = makeTree(root, 'A');

		preorder(root);
		inorder(root);
		postorder(root);
	}

	private static Node makeTree(Node node, char val) {
		if (node == null) {
			node = new Node();
			node.val = val;
			node.left = node.right = null;
		}

		char leftVal = tree[val - 'A'].leftVal;
		char rightVal = tree[val - 'A'].rightVal;

		if (leftVal >= 'A' && leftVal <= 'Z') {
			node.left = makeTree(node.left, leftVal);
		}

		if (rightVal >= 'A' && rightVal <= 'Z') {
			node.right = makeTree(node.right, rightVal);
		}

		return node;
	}

	// root -> left -> right
	private static void preorder(Node node) {
		Stack<Node> stack = new Stack<>();
		stack.push(node);

		while(!stack.isEmpty()) {
			node = stack.pop();
			System.out.print(node.val);

			if (node.right != null) { // right first! because it’s stack.
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		System.out.println();
	}

	// left -> root -> right
	private static void inorder(Node node) {
		Stack<Node> stack = new Stack<>();
		while (node != null) {
			stack.push(node);
			node = node.left;
		}

		while (!stack.isEmpty()) {
			node = stack.pop();
			System.out.print(node.val);

			if (node.right != null) {
				node = node.right;
				stack.push(node);

				while (node.left != null) {
					stack.push(node.left);
					node = node.left;
				}
			}
		}
		System.out.println();
	}

	// left -> right -> root
	private static void postorder(Node node) {
		Stack<Node> s = new Stack<>();
		Stack<Node> t = new Stack<>(); // temp stack

		while (node != null) {
			s.push(node);
			t.push(node);
			node = node.right;
		}

		while (!t.isEmpty()) {
			node = t.pop();

			if (node.left != null) {
				node = node.left;
				s.push(node);
				t.push(node);

				while (node.right != null) {
					s.push(node.right);
					t.push(node.right);
					node = node.right;
				}
			}
		}

		while (!s.isEmpty()) {
			System.out.print(s.pop().val);
		}
		
		System.out.println();
	}
	
	
/* 
	
	// This way is too restrict. because array size should be pow(2, n);
	private static char[] tree = new char[1111];
	
	public static void main(String[] args) throws Exception {
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		
		for (int i=1; i<=n ; i++) {
			char[] chars = br.readLine().toCharArray(); 
			char nodeVal = chars[0];
			char leftVal = chars[2];
			char rightVal = chars[4];
			
			if (i == 1) {
				tree[i] = nodeVal; 
				tree[i*2] = leftVal;
				tree[i*2+1] = rightVal; 
				continue;
			}
			int j=0;
			while (nodeVal != tree[++j]) { }
			tree[j] = nodeVal;
			tree[j*2] = leftVal;
			tree[j*2+1] = rightVal;
		}
		
		doPreOrder(1);
		System.out.println();
		doInOrder(1);
		System.out.println();
		doPostOrder(1);
		System.out.println();
	}
	
	private static void doPreOrder(int idx) {
		if (tree[idx] >= 'A' && tree[idx] <= 'Z') { 
			System.out.print(tree[idx]);
			doPreOrder(idx*2);
			doPreOrder(idx*2+1);
		}
	}
	
	private static void doInOrder(int idx) {
		if (tree[idx] >= 'A' && tree[idx] <= 'Z') {
			doInOrder(idx*2);
			System.out.print(tree[idx]);
			doInOrder(idx*2+1);
		}
	}
	
	private static void doPostOrder(int idx) {
		if (tree[idx] >= 'A' && tree[idx] <= 'Z') {
			doPostOrder(idx*2);
			doPostOrder(idx*2+1);
			System.out.print(tree[idx]);
		}
	}
 */

}
