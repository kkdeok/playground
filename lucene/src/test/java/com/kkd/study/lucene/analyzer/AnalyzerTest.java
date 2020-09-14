package com.kkd.study.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.junit.Test;

import java.io.IOException;

public class AnalyzerTest {
	private static final Analyzer[] analyzers = new Analyzer[]{
			new WhitespaceAnalyzer(),
			new SimpleAnalyzer(),
			new StopAnalyzer(),
			new StandardAnalyzer()};

	@Test
	public void testAnalyzer() throws IOException {
		String[] texts = {"The quick brown fox jumped over the lazy dog", "XY&Z Corporation - xyz@example.com"};
		for (String text : texts) {
			analyze(text);
		}
	}

	/**
	 * This is the way how we can see the token created by analyzer.
	 * @param text
	 * @throws IOException
	 */
	private void analyze(String text) throws IOException {
		System.out.println("analyzing \"" + text + "\"");
		for (Analyzer analyzer : analyzers) {
			String name = analyzer.getClass().getSimpleName();
			System.out.println(name + ":");
			AnalyzerUtils.displayTokens(analyzer, text);
			System.out.println("\n");
		}
	}
}
