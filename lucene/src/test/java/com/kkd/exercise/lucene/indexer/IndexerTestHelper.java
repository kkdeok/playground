package com.kkd.exercise.lucene.indexer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
public class IndexerTestHelper {

	public IndexWriter getWriter(Directory dir) throws IOException {
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		return new IndexWriter(dir, config);
	}

	public List<Book> getSourceData() throws IOException {
		final String sampleDataPath = "sample_data.json";
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource(sampleDataPath)).getFile());

		ObjectMapper mapper = new ObjectMapper();
		return Arrays.asList(mapper.readValue(file, Book[].class));
	}

	public void buildIndex(Directory dir) throws IOException {
		IndexWriter writer = getWriter(dir);
		List<Book> bookList = getSourceData();
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
