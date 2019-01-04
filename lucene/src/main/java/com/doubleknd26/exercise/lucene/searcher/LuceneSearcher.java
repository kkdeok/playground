package com.doubleknd26.exercise.lucene.searcher;

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

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * This code was originally written for Erik's Lucene intro java.net article.
 *
 * Make sure to run this class after creating index using LuceneIndexer.
 * Created by doubleknd26 on 2019-01-04.
 */
public class LuceneSearcher {

    public static void search(String indexDir, String q) throws IOException, ParseException {
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader indexReader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(indexReader);

        QueryParser parser = new QueryParser("contents", new StandardAnalyzer());
        Query query = parser.parse(q);
        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, 10);
        long end = System.currentTimeMillis();

        System.err.println("Found " + hits.totalHits +
                " document(s) (in " + (end - start) +
                " milliseconds) that matched query '" + q + "':");

        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            System.out.println(doc.get("fullpath"));
        }
    }

    /**
     * #1 Parse provided index directory
     * #2 Parse provided query string
     * #3 Open index
     * #4 Parse query
     * #5 Search index
     * #6 Write search stats
     * #7 Retrieve matching document
     * #8 Display filename
     * #9 Close IndexSearcher
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        final String dir = System.getProperty("user.dir");
        final String indexDir = dir + "/lucene/index";
        // write query here.
        String q = "patent";
        search(indexDir, q);
    }
}

