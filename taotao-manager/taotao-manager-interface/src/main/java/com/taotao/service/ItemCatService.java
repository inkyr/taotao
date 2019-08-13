package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.ItemCatResult;

import java.util.List;

public interface ItemCatService {

    List<EasyUITreeNode> getCatList(Long id);

    ItemCatResult getItemCatAll(Long parentId);

}
