package com.doubleknd26.exercise.lucene.indexer;

/**
 * Copyright Manning Publications Co.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific lan
 */

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.Directory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Paths;

/**
 * Source code was provided by Lucene in Action, Second Edition.
 * https://www.manning.com/books/lucene-in-action-second-edition
 *
 * This code was originally written for Erik's Lucene intro java.net article.
 * Created by doubleknd26 on 2019-01-04.
 */
public class LuceneIndexer {
    private IndexWriter writer;

    public LuceneIndexer(String indexDir) throws IOException {
        final Directory dir = FSDirectory.open(Paths.get(indexDir));
        this.writer = new IndexWriter(dir, new IndexWriterConfig(new SimpleAnalyzer()));

    }

    public int index(String dataDir, FileFilter filter) throws Exception {
        File[] files = new File(dataDir).listFiles();
        for (File f: files) {
            if (!f.isDirectory() &&
                    !f.isHidden() &&
                    f.exists() &&
                    f.canRead() &&
                    (filter == null || filter.accept(f))) {
                indexFile(f);
            }
        }
        return writer.numDocs();
    }

    private void indexFile(File f) throws Exception {
        System.out.println("Indexing " + f.getCanonicalPath());
        Document doc = getDocument(f);
        writer.addDocument(doc);
    }

    protected Document getDocument(File f) throws Exception {
        Document doc = new Document();
        FieldType storedFalseType = new FieldType();
        storedFalseType.setStored(false);
        storedFalseType.setIndexOptions(IndexOptions.DOCS);
        FieldType storedTrueType = new FieldType();
        storedTrueType.setStored(true);

        doc.add(new Field("contents", new FileReader(f), storedFalseType));
        doc.add(new Field("filename", f.getName(), storedTrueType));
        doc.add(new Field("fullpath", f.getCanonicalPath(), storedTrueType));
        return doc;
    }

    public void close() throws IOException {
        writer.close();
    }

    /**
     * #1 Create index in this directory
     * #2 Index *.txt files from this directory
     * #3 Create Lucene IndexWriter
     * #4 Close IndexWriter
     * #5 Return number of documents indexed
     * #6 Index .txt files only, using FileFilter
     * #7 Index file content
     * #8 Index file name
     * #9 Index file full path
     * #10 Add document to Lucene index
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        final String dir = System.getProperty("user.dir");
        final String indexDir = dir + "/lucene/index";
        final String dataDir = dir + "/lucene/src/data/";

        final long start = System.currentTimeMillis();
        LuceneIndexer indexer = new LuceneIndexer(indexDir);
        int numIndexed;
        try {
            numIndexed = indexer.index(dataDir, new TextFilesFilter());
        } finally {
            indexer.close();
        }
        final long end = System.currentTimeMillis();
        System.out.println("Indexing " + numIndexed + " files took "
                + (end - start) + " milliseconds");
    }

    private static class TextFilesFilter implements FileFilter {
        public boolean accept(File path) {
            return path.getName().toLowerCase().endsWith(".txt");
        }
    }
}

