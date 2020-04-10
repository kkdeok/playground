package com.doubleknd26.exercise.lucene.analyzer;

import java.io.IOException;

public interface SynonymEngine {
	String[] getSynonyms(String s) throws IOException;
}
