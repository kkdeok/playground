package com.doubleknd26.exercise.lucene.analyzer;

import com.doubleknd26.exercise.lucene.utils.TestUtil;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class SynonymAnalyzerTest {
	private SynonymAnalyzer synonymAnalyzer = new SynonymAnalyzer(new SynonymEngineImpl());

	@Test
	public void testSearchByApi() throws IOException {
		Directory directory = prepareIndex();
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);

		// hops 단어 검색
		TermQuery tq = new TermQuery(new Term("content", "hops"));
		assertEquals(1, TestUtil.hitCount(searcher, tq));

		// 'fox hops' 구문 검색
		// 문서에는 fox jumps .. 라는 구문이 색인되어 있으며, jump의 유사어로 hops가
		// 색인되었기 때문에 'fox hops' 라는 구문 검색의 결과에도 해당 문서가 매칭된다.
		PhraseQuery pq = new PhraseQuery.Builder()
				.add(new Term("content", "fox"))
				.add(new Term("content", "hops"))
				.build();
		assertEquals(1, TestUtil.hitCount(searcher, pq));

		directory.close();
	}

	@Test
	public void testWithQueryParser() throws Exception {
		Directory directory = prepareIndex();
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);

		Query query = new QueryParser("content", synonymAnalyzer).parse("\"fox jumps\"");
		assertEquals(1, TestUtil.hitCount(searcher, query));
		System.out.println("With SynonymAnalyzer, \"fox jumps\" parse to " + query.toString("content"));

		query = new QueryParser("content", new StandardAnalyzer()).parse("\"fox jumps\"");
		assertEquals(1, TestUtil.hitCount(searcher, query));
		System.out.println("With StandardAnalyzer, \"fox jumps\" parse to " + query.toString("content"));

		directory.close();
	}

	private Directory prepareIndex() throws IOException {
		// use SynonymAnalyzer
		RAMDirectory directory = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(synonymAnalyzer);
		IndexWriter writer = new IndexWriter(directory, config);

		Document doc = new Document();
		doc.add(new TextField("content",
				"The quick brown fox jumps over the lazy dog",
				Field.Store.YES));
		writer.addDocument(doc);
		writer.close();
		return directory;
	}


	@Test
	public void testSynonymTokens() throws Exception {
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

	@Test
	public void testSynonymAnalyzerViewer() throws IOException {
		AnalyzerUtils.displayTokensWithPositions(synonymAnalyzer, "The quick brown fox jumps over the lazy dog");
	}
}
