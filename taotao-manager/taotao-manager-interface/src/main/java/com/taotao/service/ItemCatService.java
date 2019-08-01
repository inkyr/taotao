package com.taotao.service;

import com.taotao.utils.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {

    List<EasyUITreeNode> getCatList(Long id);

}
