package com.doubleknd26.exercise.lucene.indexer;

/**
 * Copyright Manning Publications Co.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific lan
 */

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;

public class TestIndexCreator {

	public static Document getDocument(String rootDir, File file) throws IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(file));

		Document doc = new Document();

		// category comes from relative path below the base directory
		String category = file.getParent().substring(rootDir.length());
		category = category.replace(File.separatorChar, '/');

		String isbn = props.getProperty("isbn");
		String title = props.getProperty("title");
		String author = props.getProperty("author");
		String url = props.getProperty("url");
		String subject = props.getProperty("subject");
		String pubmonth = props.getProperty("pubmonth");

		System.out.println(title + "\n" + author + "\n" + subject + "\n" + pubmonth + "\n" + category + "\n---------");

		doc.add(new Field("isbn",
				isbn,
				Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field("category",
				category,
				Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		doc.add(new Field("title",
				title,
				Field.Store.YES,
				Field.Index.ANALYZED,
				Field.TermVector.WITH_POSITIONS_OFFSETS));
		doc.add(new Field("title2",
				title.toLowerCase(),
				Field.Store.YES,
				Field.Index.NOT_ANALYZED_NO_NORMS,
				Field.TermVector.WITH_POSITIONS_OFFSETS));

		// split multiple authors into unique field instances
		String[] authors = author.split(",")
		for (String a : authors) {
			doc.add(new Field("author",
					a,
					Field.Store.YES,
					Field.Index.NOT_ANALYZED,
					Field.TermVector.WITH_POSITIONS_OFFSETS));
		}

		doc.add(new Field("url",
				url,
				Field.Store.YES,
				Field.Index.NOT_ANALYZED_NO_NORMS));
		doc.add(new Field("subject",
				subject,
				Field.Store.YES,
				Field.Index.ANALYZED,
				Field.TermVector.WITH_POSITIONS_OFFSETS));

		doc.add(new NumericField("pubmonth",
				Field.Store.YES,
				true).setIntValue(Integer.parseInt(pubmonth)));

		Date d;
		try {
			d = DateTools.stringToDate(pubmonth);
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
		doc.add(new NumericField("pubmonthAsDay")
				.setIntValue((int) (d.getTime() / (1000 * 3600 * 24))));

		for (String text : new String[]{title, subject, author, category}) {
			doc.add(new Field("contents", text,
					Field.Store.NO, Field.Index.ANALYZED,
					Field.TermVector.WITH_POSITIONS_OFFSETS));
		}

		return doc;
	}

	private static String aggregate(String[] strings) {
		StringBuilder buffer = new StringBuilder();

		for (int i = 0; i < strings.length; i++) {
			buffer.append(strings[i]);
			buffer.append(" ");
		}

		return buffer.toString();
	}

	private static void findFiles(List<File> result, File dir) {
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith(".properties")) {
				result.add(file);
			} else if (file.isDirectory()) {
				findFiles(result, file);
			}
		}
	}

	private static class MyStandardAnalyzer extends StandardAnalyzer {
		@Override
		public int getPositionIncrementGap(String fieldName) {
			if (fieldName.equals("contents")) {
				return 100;
			} else {
				return 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String dataDir = "resource/data/";
		String indexDir = "index/";
		List<File> results = new ArrayList<File>();
		findFiles(results, new File(dataDir));
		System.out.println(results.size() + " books to index");

		Directory dir = FSDirectory.open(Paths.get(indexDir));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());


		IndexWriter w = new IndexWriter(dir);
		for (File file : results) {
			Document doc = getDocument(dataDir, file);
			w.addDocument(doc);
		}
		w.close();
		dir.close();
	}
}

/*
  #1 Get category
  #2 Pull fields
  #3 Add fields to Document instance
  #4 Flag subject field
  #5 Add catch-all contents field
  #6 Custom analyzer to override multi-valued position increment
*/
