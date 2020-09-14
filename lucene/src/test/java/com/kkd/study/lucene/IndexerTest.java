package com.kkd.study.lucene;

import com.kkd.study.lucene.indexer.Book;
import com.kkd.study.lucene.indexer.IndexerTestHelper;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class IndexerTest {
	private IndexerTestHelper indexer;
	private RAMDirectory dir;

	@Before
	public void setUp() throws Exception {
		this.indexer = new IndexerTestHelper();
		this.dir = new RAMDirectory();
	}

	@Test
	public void testIndexWriter() throws IOException {
		indexer.buildIndex(dir);

		List<Book> bookList = indexer.getSourceData();
		IndexWriter writer = indexer.getWriter(dir);
		assertEquals(bookList.size(), writer.numDocs());
		writer.close();
	}

	@Test
	public void testIndexReader() throws IOException {
		indexer.buildIndex(dir);

		IndexReader reader = DirectoryReader.open(dir);
		List<Book> bookList = indexer.getSourceData();
		assertEquals(bookList.size(), reader.maxDoc());
		assertEquals(bookList.size(), reader.numDocs());
		reader.close();
	}

	/**
	 * Delete document without forceMergeDeletes,
	 * which means index still contains deleted docs.
	 * @throws IOException
	 */
	@Test
	public void testDeleteBeforeForceMergeDeletes() throws IOException {
		indexer.buildIndex(dir);

		IndexWriter writer = indexer.getWriter(dir);
		writer.deleteDocuments(new Term("id", "1"));
		writer.commit();
		assertTrue(writer.hasDeletions());
		assertEquals(2, writer.maxDoc());
		assertEquals(1, writer.numDocs());
		writer.close();
	}

	/**
	 * Delete document with forceMergeDeletes,
	 * which means index does not contains deleted docs.
	 * @throws IOException
	 */
	@Test
	public void testDeleteAfterForceMergeDeletes() throws IOException {
		indexer.buildIndex(dir);

		IndexWriter writer = indexer.getWriter(dir);
		assertEquals(2, writer.numDocs());
		writer.deleteDocuments(new Term("id", "1"));
		// IndexWriter.optimize() is deprecated and removed.
		// https://lucene.apache.org/core/3_6_0/api/all/org/apache/lucene/index/IndexWriter.html#optimize()
		writer.forceMergeDeletes();
		assertFalse(writer.hasDeletions());
		assertEquals(1, writer.maxDoc());
		assertEquals(1, writer.numDocs());
		writer.close();
	}
}
