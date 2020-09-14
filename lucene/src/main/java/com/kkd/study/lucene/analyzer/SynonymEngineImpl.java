package com.kkd.study.lucene.analyzer;

import java.io.IOException;
import java.util.HashMap;

public class SynonymEngineImpl implements SynonymEngine {
	private static HashMap<String, String[]> map = new HashMap<>();

	@Override
	public String[] getSynonyms(String s) throws IOException {
		return map.get(s);
	}

	static {
		// NOTE: SynonymEngine 은 단방향 유사어만으로 구현되어 있다. 예를 들어,
		// over는 above를 가지고 있지만, above는 over를 가지고 있지 않다. 보통
		// 양뱡향 유사어로 구현한다는 점을 알아두자.
		map.put("quick", new String[]{"fast", "speedy"});
		map.put("jumps", new String[]{"leaps", "hops"});
		map.put("over", new String[]{"above"});
		map.put("lazy", new String[]{"apathetic", "sluggish"});
		map.put("dog", new String[]{"canine", "pooch"});
	}
}