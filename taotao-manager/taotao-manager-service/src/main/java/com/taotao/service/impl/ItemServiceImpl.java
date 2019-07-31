package com.taotao.service.impl;

import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public TbItem findItemById(Long itemId) {
        return itemMapper.findItemById(itemId);
    }
}
