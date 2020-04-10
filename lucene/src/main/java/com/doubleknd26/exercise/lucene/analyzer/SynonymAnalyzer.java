package com.doubleknd26.exercise.lucene.analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import java.io.Reader;

public class SynonymAnalyzer {
	private SynonymEngine engine;

	public SynonymAnalyzer(SynonymEngine engine) {
		this.engine = engine;
	}

	public TokenStream tokenStream(String fieldName, Reader reader) {
		final StandardTokenizer st = new StandardTokenizer();
		st.setReader(reader);
		final TokenStream result = new SynonymFilter(new StopFilter(new LowerCaseFilter(new StandardFilter(st)), StopAnalyzer.ENGLISH_STOP_WORDS_SET), engine);
		return result;
	}
}
