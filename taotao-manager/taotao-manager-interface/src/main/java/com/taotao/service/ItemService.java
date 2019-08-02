package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemDesc;

public interface ItemService {

    TbItem findItemById(Long itemId);

    EasyUIResult findItem(int page, int rows);

    TaotaoResult delItems(Integer[] ids);

    TaotaoResult updateDownItem(Integer[] ids);

    TaotaoResult updateUpItem(Integer[] ids);

    TaotaoResult addItem(TbItem tbItem, TbItemDesc tbItemDesc);
}
