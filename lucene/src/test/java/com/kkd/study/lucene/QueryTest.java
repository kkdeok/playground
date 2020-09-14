package com.kkd.study.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueryTest {

	/**
	 * Query 객체가 의도대로 생성됐는지 확인하려면 Query.toString()을 사용하라.
	 */
	@Test
	public void testToString() {
		BooleanQuery query = new BooleanQuery.Builder()
				.add(new FuzzyQuery(new Term("field", "kountry")), BooleanClause.Occur.MUST)
				.add(new TermQuery(new Term("title", "western")), BooleanClause.Occur.SHOULD)
				.build();

		String expected = "+kountry~2 title:western";
		assertEquals(expected, query.toString("field"));
	}

	@Test
	public void testTermQuery() throws ParseException {
		Analyzer analyzer = new WhitespaceAnalyzer();
		// f: the default field for query terms.
		QueryParser parser = new QueryParser("subject", analyzer);
		Query query = parser.parse("computers");

		String expected = "subject:computers";
		assertEquals(expected, query.toString());
	}

	/**
	 * wildcard query & prefix query 모두 텀을 소문자로 변경한다.
	 */
	@Test
	public void testLowerCasing() throws ParseException {
		QueryParser qp = new QueryParser("field", new WhitespaceAnalyzer());
		String query = "PrefixQuery*";
		Query query1 = qp.parse(query);
		assertEquals("lowercased", query.toLowerCase(), query1.toString("field"));

		// 소문자로 변경되지 않게 설정할 수도 있다.
		qp.setLowercaseExpandedTerms(false);
		Query query2 = qp.parse(query);
		assertEquals("not lowercased", query, query2.toString("field"));
	}

	@Test
	public void testQueryParserDefaultOperatorForBooleanQuery() throws ParseException {
		QueryParser qp = new QueryParser("contents", new WhitespaceAnalyzer());
		String query = "옷걸이 행거";
		Query q = qp.parse(query);
		String expected = query;
		assertEquals("with operator OR", expected, q.toString("contents"));

		qp.setDefaultOperator(QueryParser.Operator.AND);
		q = qp.parse(query);
		expected = "+옷걸이 +행거";
		assertEquals("with operator AND", expected, q.toString("contents"));
	}

	@Test
	public void testPhraseQuery() throws ParseException {
		QueryParser qp = new QueryParser("field", new StandardAnalyzer());
		Query q = qp.parse("\"This is Some Phrase*\"");
		// NOTE: 맨 뒤에 *가 있음에도 불구하고, prefix query로 인식하지 않는 이유는 *보다 "" 의
		// 우선순위가 더 높기 때문이다. * 는 ""로 묶여있으므로, 이 쿼리는 phrase query로 인식된다.
		// NOTE: StandardAnalyzer는 This 와 is 를 stopword로 제거하고, 이 공간은 ? 로 남겨둔다.
		String expected = "\"? ? some phrase\"";
		assertEquals(expected, q.toString("field"));

		// NOTE: 분석한 결과로 Term이 하나밖에 없다면, phrase query 대신 term query를 생성한다.
		q = qp.parse("\"term\"");
		assertTrue("reduced to TermQuery", q instanceof TermQuery);
	}

	@Test
	public void testMatchAllDocsQuery() throws ParseException {
		QueryParser qp = new QueryParser("field", new StandardAnalyzer());
		Query q = qp.parse("*:*");
		assertTrue(q instanceof MatchAllDocsQuery);
	}
}
