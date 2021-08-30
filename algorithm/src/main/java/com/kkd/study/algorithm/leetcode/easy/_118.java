package com.kkd.study.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
 */
public class _118 {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();

		for (int i=0 ; i<numRows ; i++) {
			List<Integer> row = new ArrayList<>();
			if (i == 0) {
				row.add(1);
				res.add(row);
			} else {
				int min = 0;
				int max = i;
				for (int j=0 ; j<=i ; j++) {
					if (j-1>=min && j<max) row.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
					else row.add(1);
				}
				res.add(row);
			}
		}
		return res;
	}
}
