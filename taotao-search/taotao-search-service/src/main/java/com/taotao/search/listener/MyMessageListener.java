package com.taotao.search.listener;

import com.taotao.common.pojo.SearchItem;
import com.taotao.mapper.SearchItemMapper;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

public class MyMessageListener implements MessageListener {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SearchItemService searchItemService;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String id = textMessage.getText();
            if (id.split("@")[0].equals("add")) {
                SearchItem itemById = searchItemMapper.getItemById(Long.valueOf(id.split("@")[1]));
                searchItemService.addDocument(itemById);
            } else if (id.split("@")[0].equals("del")) {
                String[] split = id.split("@");
                List<String> ids = new ArrayList<>();
                for (String s : split) {
                    if (!s.equals("del")) {
                        ids.add(s);
                    }
                }
                searchItemService.delDocument(ids);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
