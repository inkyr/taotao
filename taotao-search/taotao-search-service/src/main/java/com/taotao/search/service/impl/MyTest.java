package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

public class MyTest {

    @Test
    public void demo1() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.145.150:9090/solr");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "test001");
        document.addField("item_title", "测试商品");
        document.addField("item_price", "199");
        solrServer.add(document);
        solrServer.commit();
    }
}
