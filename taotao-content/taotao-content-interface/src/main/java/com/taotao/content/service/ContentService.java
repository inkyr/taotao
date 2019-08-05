package com.taotao.content.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

    EasyUIResult findContentAll(Long contentCategoryId);

    TaotaoResult addContent(TbContent tbContent);
}
