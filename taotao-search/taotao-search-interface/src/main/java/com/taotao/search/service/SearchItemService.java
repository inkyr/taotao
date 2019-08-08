package com.taotao.search.service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

public interface SearchItemService {

    TaotaoResult importAllItems();

    /**
     * 搜索服务
     * @param queryString 查询条件
     * @param page 当前页
     * @param rows 每一页显示的条数
     * @return
     */
    SearchResult search(String queryString, int page, int rows);
}
