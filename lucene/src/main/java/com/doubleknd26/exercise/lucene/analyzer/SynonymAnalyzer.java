package com.doubleknd26.exercise.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;


public class SynonymAnalyzer extends Analyzer {
	private SynonymEngine engine;

	public SynonymAnalyzer(SynonymEngine engine) {
		this.engine = engine;
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		StandardTokenizer tokenizer = new StandardTokenizer();
		TokenStream tokenStream  = new SynonymFilter(
				new StopFilter(
						new LowerCaseFilter(new StandardFilter(tokenizer))
						, StopAnalyzer.ENGLISH_STOP_WORDS_SET)
				, engine);
		TokenStreamComponents tsc = new TokenStreamComponents(tokenizer, tokenStream);
		return tsc;
	}
}
