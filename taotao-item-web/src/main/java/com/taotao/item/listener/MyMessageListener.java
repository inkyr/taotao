package com.taotao.item.listener;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyMessageListener implements MessageListener {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemParamService itemParamService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private BufferedWriter bufferedWriter;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            if (text.split(text)[0].equals("add")) {
                TbItem tbItem = itemService.getItemById(Long.valueOf(text));
                TbItemDesc tbItemDesc = itemService.getItemDescById(Long.valueOf(text));
                String itemParamByItemId = itemParamService.getItemParamByItemId(Long.valueOf(text));
                Template template = freeMarkerConfigurer.getConfiguration().getTemplate("item.ftl");
                Map map = new HashMap();
                map.put("item", new Item(tbItem));
                map.put("itemDesc", tbItemDesc);
                map.put("itemParam", itemParamByItemId);
                bufferedWriter = new BufferedWriter(new FileWriter("E:\\" + tbItem.getId() + ".html"));
                template.process(map, bufferedWriter);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            if(bufferedWriter == null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
