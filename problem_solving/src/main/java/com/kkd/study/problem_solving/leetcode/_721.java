package com.kkd.study.problem_solving.leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/accounts-merge/
 */
public class _721 {
	// CHECK
	// - Two accounts definitely belong to the same person if there is some email that is common 
	// to both accounts.
	// - 이메일이 겹치면 같은 사람이다.
	// - Note that even if two accounts have the same name, they may belong to different people 
	// could have the same name.
	// - The length of accounts will be in the range [1, 1000].
	// - The length of accounts[i] will be in the range [1, 10].
	// - The length of accounts[i][j] will be in the range [1, 30]. // e.g) email size 

	// DESCRIBE
	// input:
	// [John, A, B]
	// [John, D]
	// [John, B, C]
	// [Mary, A]
	//
	// output:
	// [John, A, B, C]
	// [John, D]
	// [Mary, A]

	// idea 1.
	// map인데, key를 중복할 수 있는 map.

	// idea 2.
	// 1. Make Account class {name, Set<String> email}
	// 2. Compare i=0 ... i<accounts.size() to merge it.

	// IMPLEMENTS
	// This is too slow. O(n^2)
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		List<Account> accountList = new ArrayList<>();
		for (List<String> list : accounts) {
			accountList.add(new Account(list));
		}

		// O(n^2)
		for (int i = 0; i < accountList.size(); i++) {
			Account base = accountList.get(i);
			if (base.getIsMerged()) {
				continue;
			}

			for (int j = i + 1; j < accountList.size(); j++) {
				Account target = accountList.get(j);
				if (!target.getIsMerged() && base.canMerge(target)) {
					base.merge(target);
					j = i;
				}
			}
		}

		List<List<String>> ret = new ArrayList<>();
		for (Account account : accountList) {
			if (!account.getIsMerged()) {
				ret.add(account.toList());
			}
		}
		return ret;
	}

	private class Account {
		private String name;
		private Set<String> emails;
		private boolean isMerged;

		// constructor
		public Account(List<String> list) {
			this.isMerged = false;
			this.emails = new TreeSet<>();

			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					this.name = list.get(i);
				} else {
					this.emails.add(list.get(i));
				}
			}
		}

		public String getName() {
			return name;
		}

		public Set<String> getEmails() {
			return emails;
		}

		public void setIsMerged(boolean isMerged) {
			this.isMerged = isMerged;
		}

		public boolean getIsMerged() {
			return isMerged;
		}

		public boolean canMerge(Account other) {
			if (!this.name.equals(other.getName())) {
				return false;
			}

			for (String email : other.getEmails()) {
				if (this.emails.contains(email)) {
					return true;
				}
			}
			return false;
		}

		public void merge(Account other) {
			assert this.name.equals(other.getName());
			for (String email : other.getEmails()) {
				this.emails.add(email);
			}
			other.setIsMerged(true);
		}

		public List<String> toList() {
			List<String> ret = new ArrayList<>();
			ret.add(this.name);
			ret.addAll(this.emails);
			return ret;
		}
	}

	public static void main(String[] args) {

	}
}
