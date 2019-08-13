package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.ItemDescMapper;
import com.taotao.mapper.ItemMapper;
import com.taotao.mapper.ItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination topicDestination;

    @Override
    public TbItem findItemById(Long itemId) {
        return itemMapper.findItemById(itemId);
    }

    @Override
    public EasyUIResult findItem(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<TbItem> items = itemMapper.findItemAll();
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        EasyUIResult result = new EasyUIResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(items);
        return result;
    }

    @Override
    public TaotaoResult delItems(Long[] ids) {
        int i = itemMapper.delItems(ids);
        if (i != 0) {
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult updateDownItem(Long[] ids) {
        int i = itemMapper.downItem(ids);
        if (i != 0) {
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult updateUpItem(Long[] ids) {
        int i = itemMapper.upItem(ids);
        if (i != 0) {
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult addItem(TbItem tbItem, String desc, String itemParams) {
        final long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setStatus((byte) 1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        int i = itemMapper.addItem(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        int k = itemDescMapper.addItemDesc(tbItemDesc);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setItemId(tbItem.getId());
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        int j = itemParamItemMapper.addItemParamItem(tbItemParamItem);

        if (i != 0 && k != 0 && j != 0) {
            jmsTemplate.send(topicDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage(itemId + "");
                    return textMessage;
                }
            });
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500, "添加数据失败", null);
    }

    @Override
    public TaotaoResult findItemDescById(Long itemId) {
        TbItemDesc itemDesc = itemDescMapper.findItemDescById(itemId);
        if (itemDesc != null) {
            return TaotaoResult.build(200, "ok", itemDesc);
        }
        return null;
    }

    @Override
    public TbItem getItemById(Long itemId) {
        return itemMapper.getItemById(itemId);
    }

    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        return itemMapper.getItemDescById(itemId);
    }


}
