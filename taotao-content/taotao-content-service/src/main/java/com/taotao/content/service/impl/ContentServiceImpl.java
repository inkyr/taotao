package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public EasyUIResult findContentAll(Long contentCategoryId) {
        List<TbContent> contents = contentMapper.findContentByCategoryId(contentCategoryId);
        EasyUIResult result = new EasyUIResult();
        result.setTotal((long) contents.size());
        result.setRows(contents);
        return result;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        contentMapper.addContent(tbContent);
        return TaotaoResult.ok(tbContent);
    }
}
