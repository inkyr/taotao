package com.taotao.search.listener;

import com.taotao.common.pojo.SearchItem;
import com.taotao.mapper.SearchItemMapper;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SearchItemService searchItemService;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String itemId = textMessage.getText();
            SearchItem itemById = searchItemMapper.getItemById(Long.valueOf(itemId));
            searchItemService.addDocument(itemById);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
