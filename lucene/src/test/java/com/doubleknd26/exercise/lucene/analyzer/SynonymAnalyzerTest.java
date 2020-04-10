package com.doubleknd26.exercise.lucene.analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class SynonymAnalyzerTest {

	@Test
	public void testJumps() throws Exception {
		SynonymAnalyzer synonymAnalyzer = new SynonymAnalyzer(new SynonymEngineImpl());
		TokenStream stream = synonymAnalyzer.tokenStream("contents", new StringReader("jumps"));
		CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
		PositionIncrementAttribute posIncr = stream.addAttribute(PositionIncrementAttribute.class);

		int i = 0;
		String[] expected = new String[] {"jumps", "hops", "leaps"};

		stream.reset();
		while (stream.incrementToken()) {
			assertEquals(expected[i], String.valueOf(term.toString()));

			int expectedPosition = i == 0 ? 1 : 0;
			assertEquals(expectedPosition, posIncr.getPositionIncrement());
			i++;
		}
		stream.close();
		assertEquals(3, i);
	}
}
