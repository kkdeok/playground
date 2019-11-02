package com.doubleknd26.exercise.lucene;

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

import com.doubleknd26.exercise.lucene.utils.TestUtil;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.miscellaneous.LimitTokenCountAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Source code was provided by Lucene in Action, Second Edition.
 * https://www.manning.com/books/lucene-in-action-second-edition
 *
 * Created by doubleknd26 on 2019-01-05.
 */
public class IndexingTest {
    private static final String[] ids = {"1", "2"};
    private static final String[] unindexed = {"Netherlands", "Italy"};
    private static final String[] unstored = {"Amsterdam has lots of bridges", "Venice has lots of canals"};
    private static final String[] text = {"Amsterdam", "Venice"};
    private Directory directory;

    /**
     * Add documents.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.directory = new RAMDirectory();
        IndexWriter writer = getWriter();
        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            // StringField is a way to index and store an un-analyzed field.
            doc.add(new StringField("id", ids[i], Field.Store.YES));
            doc.add(new StoredField("country", unindexed[i]));
            doc.add(new TextField("contents", unstored[i], Field.Store.NO));
            doc.add(new TextField("city", text[i], Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.close();
    }

    private IndexWriter getWriter() throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(new WhitespaceAnalyzer());
        return new IndexWriter(directory, config);
    }

    private long getHitCount(String fieldName, String searchString)
            throws IOException {
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        Term t = new Term(fieldName, searchString);
        Query query = new TermQuery(t);
        long hitCount = TestUtil.hitCount(searcher, query);
        reader.close();
        return hitCount;
    }

    @Test
    public void testIndexWriter() throws IOException {
        IndexWriter writer = getWriter();
        assertEquals(ids.length, writer.numDocs());
        writer.close();
    }

    @Test
    public void testIndexReader() throws IOException {
        IndexReader reader = DirectoryReader.open(directory);
        assertEquals(ids.length, reader.maxDoc());
        assertEquals(ids.length, reader.numDocs());
        reader.close();
    }

    /**
     * Delete document without forceMergeDeletes,
     * which means index still contains deleted docs.
     * @throws IOException
     */
    @Test
    public void testDeleteBeforeForceMergeDeletes() throws IOException {
        IndexWriter writer = getWriter();
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
        IndexWriter writer = getWriter();
        assertEquals(2, writer.numDocs());
        writer.deleteDocuments(new Term("id", "1"));
        writer.forceMergeDeletes();
        assertFalse(writer.hasDeletions());
        assertEquals(1, writer.maxDoc());
        assertEquals(1, writer.numDocs());
        writer.close();
    }

    /**
     * #A Create new document with "Haag" in city field
     * #B Replace original document with new version
     * #C Verify old document is gone
     * #D Verify new document is indexed
     * @throws IOException
     */
    @Test
    public void testUpdate() throws IOException {
        assertEquals(1, getHitCount("city", "Amsterdam"));
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id", "1", Field.Store.YES));
        doc.add(new StoredField("country", "Netherlands"));
        doc.add(new TextField("contents", "Den Haag has a lot of museums", Field.Store.NO));
        doc.add(new TextField("city", "Den Haag", Field.Store.YES));
        // delete id:1 from the index and update doc.
        writer.updateDocument(new Term("id", "1"), doc);
        writer.close();
        assertEquals(0, getHitCount("city", "Amsterdam"));
        assertEquals(1, getHitCount("city", "Haag"));
    }

    /**
     * #1 One initial document has bridges
     * #2 Create writer with maxFieldLength 1
     * #3 Index document with bridges
     * #4 Document can't be found
     * @throws IOException
     */
    @Test
    public void testMaxFieldLength() throws IOException {
        assertEquals(1, getHitCount("contents", "bridges"));
        // TODO: need to dive deep into LimitTokenCountAnalyzer.
        IndexWriterConfig config = new IndexWriterConfig(
                new LimitTokenCountAnalyzer(new WhitespaceAnalyzer(), 1));
        IndexWriter writer = new IndexWriter(directory, config);
        Document doc = new Document();
        doc.add(new TextField("contents", "these bridges can't be found", Field.Store.NO));
        writer.addDocument(doc);
        writer.close();
        assertEquals(1, getHitCount("contents", "bridges"));
    }
}

