package com.kkd.study.solr;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrCore;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class SolrIndexer {
	private CoreContainer container;
	private EmbeddedSolrServer server;

	public static void main(String[] args) throws IOException, SolrServerException {
		SolrIndexer indexer = new SolrIndexer();
		indexer.buildIndex();
	}

	public void buildIndex() throws IOException, SolrServerException {
		File tmpDir = Files.createTempDir();
		System.out.println("temp dir: " + tmpDir);
		Files.write("<solr></solr>".getBytes(), new File(tmpDir, "solr.xml"));
		List<String> filesToCopy = ImmutableList.of("/schema.xml", "/solrconfig.xml");
		for (String file : filesToCopy) {
			File tmpFile = new File(tmpDir, "configsets/product/config");
			Files.createParentDirs(tmpFile);
			Files.copy(new File(getClass().getResource(file).getFile()),
				new File(tmpDir, "configsets/product/config"));
		}

		container = new CoreContainer(tmpDir.getAbsolutePath());
		container.load();
		Map<String, String> params = ImmutableMap.of("configSet", "product", "dataDir", "data");
		SolrCore core = container.create("test", params);
		server = new EmbeddedSolrServer(core);

		SolrInputDocument document = new SolrInputDocument();
		document.setField("id", "hello, world!");
		server.add(document);

		server.commit();
		server.close();
	}
}
