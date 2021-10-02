package com.kkd.study.algorithm.baemin.exam_211002.no4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _4 {
	public static void main(String[] args) {
		_4 program = new _4();
	}

	/**
	 * 이름이 서로 다른 파일들이 한 폴더안에 있다.
	 * 파일 중에 파일명이 서로 뒤바뀌어 있거나 잘못 쓰인 경우가 있어서 이를 정확한 파일명으로 수정하려고 한다.
	 * 이때 수정 횟수를 ***최소로 하여 작업을 완료하고자 한다.
	 * 한번에 하나의 파일명만 수정할 수 있으며, 파일의 위치를 옮길 수는 없다.
	 *
	 * 이름이 같은 두 개의 파일이 동시에 존재할 수 없다.
	 * 만약 파일 명을 수정할 때 같은 이름을 가진 다른 파일이 존재한다면, 덮어쓰기로 인해 기존의 파일이 삭제된다.
	 * 덮어쓰기를 피하기 위해 임시 파일명을 사용할 수 있다.
	 */
	public int solution(String[] before, String[] after) {
		boolean[] temp = new boolean[before.length];
		int ans = 0;

		Set<String> current = new HashSet<>(Arrays.asList(before));

		for (int i=0 ; i<before.length ; i++) {
			if (before[i].equals(after[i])) continue;

			if (!current.contains(after[i])) {
				current.remove(before[i]);
				before[i] = after[i];
				ans++;
				current.add(after[i]);
			} else {

			}
		}

		for (int i=0 ; i<before.length ; i++) {


		}

		return ans;
	}
}
