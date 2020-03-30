package com.doubleknd26.exercise.lucene.indexer;

import com.doubleknd26.exercise.lucene.indexer.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Build index using sample data
 */
public class TestIndexer {
	private static final String SAMPLE_DATA_PATH = "sample_data.json";
	private IndexWriterConfig config;

	public TestIndexer() {
		this.config = new IndexWriterConfig(new WhitespaceAnalyzer());
	}

	public void buildIndex(Directory dir) throws IOException {
		IndexWriter writer = new IndexWriter(dir, config);

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource(SAMPLE_DATA_PATH)).getFile());

		ObjectMapper mapper = new ObjectMapper();
		List<Book> bookList = Arrays.asList(mapper.readValue(file, Book[].class));

		for (Book book : bookList) {
			Document doc = new Document();
			doc.add(new StringField("id", String.valueOf(book.getId()), Field.Store.YES));
			doc.add(new StringField("name", book.getName(), Field.Store.YES));
			doc.add(new StringField("writer", book.getWriter(), Field.Store.YES));
			doc.add(new StringField("pageCount", String.valueOf(book.getPageCount()), Field.Store.YES));
			doc.add(new StringField("publishedDate", book.getPublishedDate(), Field.Store.YES));
			doc.add(new TextField("intro", book.getIntro(), Field.Store.YES));
			writer.addDocument(doc);
		}
		writer.close();
	}
}
