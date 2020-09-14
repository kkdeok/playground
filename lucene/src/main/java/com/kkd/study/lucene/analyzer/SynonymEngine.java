package com.kkd.study.lucene.analyzer;

import java.io.IOException;

public interface SynonymEngine {
	String[] getSynonyms(String s) throws IOException;
}
