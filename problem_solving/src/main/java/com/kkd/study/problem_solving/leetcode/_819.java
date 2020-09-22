package com.kkd.study.problem_solving.leetcode;

import java.util.*;

public class _819 {
	// PROBLEM

	// CHECK
	// Q. word는 space로 구분하나요? No, 

	// IDEA 1.
	// 1. make paragraph to lower case.
	// 2. split paragraph using all puctuation symbols.
	// 3. remove banned words from the map.
	// 4. sorted map by value.
	private static Set<String> punctuations = new HashSet<>();
	static { punctuations.addAll(Arrays.asList("\\!", "\\?", "\\,", "\\;", "\\.", "\\'")); }
	
	public String mostCommonWord(String paragraph, String[] banned) {
		// 1. make paragraph to lower case.
		paragraph = paragraph.toLowerCase();

		// 2. split paragraph using all puctuation symbols. ( 6 * 1000 = paragraph length )
		List<String> words = new ArrayList(Arrays.asList(paragraph.split(" ")));
		for (String punctuation : punctuations) {
			List<String> temp = new ArrayList<>();
			for (String word : words) {
				temp.addAll(Arrays.asList(word.split(punctuation)));
			}
			words = temp;
		}

		// count words
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			int cnt = map.getOrDefault(word, 0);
			map.put(word, cnt + 1);
		}

		// 3. remove banned words from the map.
		for (String ban : banned) {
			map.remove(ban);
		}

		// 4. sorted map by value. O(n log n)
		List<String> keys = new ArrayList<>(map.keySet());
		keys.sort((a, b) -> Integer.compare(map.get(b), map.get(a)));

		return keys.get(0);
	}

	public static void main(String[] args) {
		_819 program = new _819();
		String ret = program.mostCommonWord(
			"j. t? T. z! R, v, F' x! L; l! W. M; S. y? r! n; O. q; I? h; w. t; y; X? y, p. k! k, h, J, r? w! U! V; j' u; R! z. s. T' k. P? M' I' j! y. P, T! e; X. w? M! Y, X; G; d, X? S' F, K? V, r' v, v, D, w, K! S? Q! N. n. V. v. t? t' x! u. j; m; n! F, V' Y! h; c! V, v, X' X' t? n; N' r; x. W' P? W; p' q, S' X, J; R. x; z; z! G, U; m. P; o. P! Y; I, I' l' J? h; Q; s? U, q, x. J, T! o. z, N, L; u, w! u, S. Y! V; S? y' E! O; p' X, w. p' M, h! R; t? K? Y' z? T? w; u. q' R, q, T. R? I. R! t, X, s? u; z. u, Y, n' U; m; p? g' P? y' v, o? K? R. Q? I! c, X, x. r' u! m' y. t. W; x! K? B. v; m, k; k' x; Z! U! p. U? Q, t, u' E' n? S' w. y; W, x? r. p! Y? q, Y. t, Z' V, S. q; W. Z, z? x! k, I. n; x? z; V? s! g, U; E' m! Z? y' x? V! t, F. Z? Y' S! z, Y' T? x? v? o! l; d; G' L. L, Z? q. w' r? U! E, H. C, Q! O? w! s? w' D. R, Y? u. w, N. Z? h. M? o, B, g, Z! t! l, W? z, o? z, q! O? u, N; o' o? V; S! z; q! q. o, t! q! w! Z? Z? w, F? O' N' U' p? r' J' L; S. M; g' V. i, P, v, v, f; W? L, y! i' z; L? w. v, s! P?"
			, new String[]{"m","q","e","l","c","i","z","j","g","t","w","v","h","p","d","b","a","r","x","n"});
		System.out.println(ret);
	}
}
