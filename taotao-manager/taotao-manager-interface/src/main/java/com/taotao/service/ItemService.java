package com.taotao.service;

import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItem;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

public interface ItemService {

    TbItem findItemById(Long itemId);

    EasyUIResult findItem(int page, int rows);

    TaotaoResult delItems(Long[] ids);

    TaotaoResult updateDownItem(Long[] ids);

    TaotaoResult updateUpItem(Long[] ids);

    TaotaoResult addItem(TbItem tbItem, String desc, String itemParams);

    TaotaoResult findItemDescById(Long itemId);

    TbItem getItemById(Long itemId);

    TbItemDesc getItemDescById(Long itemId);

    TaotaoResult updateItem(TbItem tbItem, String itemParams, String desc, Long itemParamId);

    TaotaoResult updateContent(TbContent tbContent);

    //TaotaoResult updateDesc(String desc, Long id);
}