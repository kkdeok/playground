package com.doubleknd26.exercise.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

public class AnalyzerUtils {

	public static void displayTokens(Analyzer analyzer, String text) throws IOException {
		displayTokens(analyzer.tokenStream("contents", new StringReader(text)));
	}

	private static void displayTokens(TokenStream stream) throws IOException {
		CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
		stream.reset();
		while (stream.incrementToken()) {
			System.out.print("[" + term + "] ");
		}
		stream.close();
	}
}
