package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemDesc;

public interface ItemService {

    TbItem findItemById(Long itemId);

    EasyUIResult findItem(int page, int rows);

    TaotaoResult delItems(Long[] ids);

    TaotaoResult updateDownItem(Long[] ids);

    TaotaoResult updateUpItem(Long[] ids);

    TaotaoResult addItem(TbItem tbItem, TbItemDesc tbItemDesc);

    TaotaoResult findItemDescById(Long itemId);
}
