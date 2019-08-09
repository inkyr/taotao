package com.taotao.search.service.impl;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import javax.jms.*;
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

    @Test
    public void demo2(){
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.145.150:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("test-queue");
            MessageProducer producer = session.createProducer(queue);
            TextMessage textMsg = session.createTextMessage("dididididid");
            producer.send(textMsg);
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo3(){
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.145.150:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("test-queue");
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        TextMessage textMessage = (TextMessage) message;
                        String text = null;
                        //取消息的内容
                        text = textMessage.getText();
                        // 第八步：打印消息。
                        System.out.println(text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.in.read();
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
