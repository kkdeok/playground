package com.doubleknd26.exercise.lucene;

import com.doubleknd26.exercise.lucene.indexer.TestIndexer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class IndexerTest {
	private TestIndexer indexer;

	@Before
	public void setUp() throws Exception {
		this.indexer = new TestIndexer();
	}

	@Test
	public void numDocs() throws IOException {
		RAMDirectory directory = new RAMDirectory();
		indexer.buildIndex(directory);

		IndexReader reader = DirectoryReader.open(directory);
		assertEquals(2, reader.numDocs());
		reader.close();
	}

	@Test
	public void maxDoc() throws IOException {
		RAMDirectory directory = new RAMDirectory();
		indexer.buildIndex(directory);

		IndexReader reader = DirectoryReader.open(directory);
		assertEquals(2, reader.maxDoc());
		reader.close();
	}
}
