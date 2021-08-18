package com.kkd.study.algorithm.baekjoon.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/5639
 */
public class _5639 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static List<Integer> list = new ArrayList<>();
	
	private static class Node {
		int val;
		Node left, right;
	}

	public static void main(String[] args) throws Exception {
		while (true) {
			try {
				list.add(Integer.parseInt(br.readLine()));
			} catch (Exception e) {
				break;
			}
		}
		
		Node root = makeTree(0, list.size());
		if (root != null) {
			postTraversal(root);
		}
	}

	private static Node makeTree(int s, int e) {
		if (s == e) return null;

		Node node = new Node();
		node.val = list.get(s);

		int mid = s+1;
		for (int i=s+1 ; i<=e ; i++) {
			mid = i;
			if (i == e || list.get(i) > list.get(s)) break;
		}

		node.left = makeTree(s+1, mid);
		node.right = makeTree(mid, e);
		return node;
	}

	// left -> right -> root
	private static void postTraversal(Node node) {
		Stack<Node> s = new Stack<>();
		Stack<Node> t = new Stack<>();

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
			System.out.println(s.pop().val);
		}
	}
}
