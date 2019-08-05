package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCategoryList(Long parentId);

    TaotaoResult addContentCategory(Long parentId, String name);

    void updateContentCategory(Long id, String name);

    void deleteContentCategory(Long id);

    void deleteAndChangeState(Long id);

    void delete(TbContentCategory tbContentCategory);
}
