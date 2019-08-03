package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.ItemDescMapper;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

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
    public TaotaoResult addItem(TbItem tbItem, TbItemDesc tbItemDesc) {
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setStatus((byte) 1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        int i = itemMapper.addItem(tbItem);

        tbItemDesc.setItemId(itemId);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        int k = itemDescMapper.addItemDesc(tbItemDesc);

        if(i != 0 && k != 0){
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500, "添加数据失败", null);
    }

    @Override
    public TaotaoResult findItemDescById(Long itemId) {
        TbItemDesc itemDesc = itemDescMapper.findItemDescById(itemId);
        if(itemDesc != null){
            return TaotaoResult.build(200, "ok", itemDesc);
        }
        return null;
    }

}
