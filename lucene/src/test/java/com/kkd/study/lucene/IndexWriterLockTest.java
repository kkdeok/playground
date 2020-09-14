package com.kkd.study.lucene;

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
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Source code referred Lucene in Action, Second Edition.
 * https://www.manning.com/books/lucene-in-action-second-edition
 *
 * Created by doubleknd26 on 2019-01-10.
 */
public class IndexWriterLockTest {
    private Directory dir;
    private Path indexPath;
    private IndexWriter writer1;
    private IndexWriter writer2;

    @Before
    public void setUp() throws IOException {
        this.indexPath = Paths.get(
                System.getProperty("java.io.tmpdir", "tmp")
                + System.getProperty("file.separator") + "index");
        this.dir = FSDirectory.open(indexPath);
    }

    @After
    public void tearDown() throws Exception {
        if (writer1 != null) {
            writer1.close();
        }
        if (writer2 != null) {
            writer2.close();
        }
    }

    /**
     * A Expected exception: only one IndexWriter allowed to access index at once
     *
     * @throws IOException
     */
    @Test(expected = LockObtainFailedException.class)
    public void testWriteLock() throws IOException {
        IndexWriterConfig config1 = new IndexWriterConfig(new SimpleAnalyzer());
        writer1 = new IndexWriter(dir, config1);

        IndexWriterConfig config2 = new IndexWriterConfig(new SimpleAnalyzer());
        writer2 = new IndexWriter(dir, config2);
    }
}
