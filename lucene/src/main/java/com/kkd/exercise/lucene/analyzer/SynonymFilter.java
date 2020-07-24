package com.kkd.exercise.lucene.analyzer;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;

import java.io.IOException;
import java.util.Stack;

public final class SynonymFilter extends TokenFilter {
	public static final String TOKEN_TYPE_SYNONYM = "SYNONYM";

	private Stack<String> synonymStack;
	private SynonymEngine engine;
	private AttributeSource.State current;

	private final CharTermAttribute termAttr;
	private final PositionIncrementAttribute posIncrAttr;


	public SynonymFilter(TokenStream in, SynonymEngine engine) {
		super(in);
		this.synonymStack = new Stack<>(); // 유사어를 담아둘 버퍼.
		this.engine = engine;
		this.termAttr = addAttribute(CharTermAttribute.class);
		this.posIncrAttr = addAttribute(PositionIncrementAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		if (synonymStack.size() > 0) {
			String synonym = synonymStack.pop();
			restoreState(current);
			termAttr.setEmpty().append(synonym);
			posIncrAttr.setPositionIncrement(0);
			return true;
		}

		if (!input.incrementToken()) {
			return false;
		}

		if (addAliasesToStack()) {
			current = captureState();
		}
		return true;
	}

	private boolean addAliasesToStack() throws IOException {
		String[] synonyms = engine.getSynonyms(termAttr.toString());
		if (synonyms == null) {
			return false;
		}
		for (String synonym : synonyms) {
			synonymStack.push(synonym);
		}
		return true;
	}
}
