package com.kkd.study.algorithm.baemin.exam_211002.no1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class _1 {
	public static void main(String[] args) {
		_1 program = new _1();
		String result = program.solution("QWERTYUIOASDFGHJKLZXCVBNM");
		System.out.println(result);
	}

	public String solution(String message) {
		String answer = "";

		message = " " + message;
		char[] tree = message.toCharArray();
		List<Character> list = new ArrayList<>();

		traversal(list, tree, 1);
		answer = list.stream().map(String::valueOf).collect(Collectors.joining());
		return answer;
	}

	private void traversal(List<Character> list, char[] tree, int key) {
		if (key <= tree.length -1){
			traversal(list, tree, key * 2);
			traversal(list, tree, key * 2 + 1);
			list.add(tree[key]);
		}
	}
}
