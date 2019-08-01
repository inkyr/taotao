package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.utils.EasyUIResult;
import com.taotao.utils.TaotaoResult;

public interface ItemService {

    TbItem findItemById(Long itemId);

    EasyUIResult findItem(int page, int rows);

    TaotaoResult delItems(Integer[] ids);

    TaotaoResult updateDownItem(Integer[] ids);

    TaotaoResult updateUpItem(Integer[] ids);
}
