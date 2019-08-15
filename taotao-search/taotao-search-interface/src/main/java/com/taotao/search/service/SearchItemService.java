package com.taotao.search.service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemCat;

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

    void addDocument(SearchItem itemById);

    SearchResult searchProducts(String name, int page, int rows);

    TbItemCat getItemCatById(Long id);
}
