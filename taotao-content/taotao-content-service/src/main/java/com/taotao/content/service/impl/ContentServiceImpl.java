package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JedisUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.TbContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    @Value("${ITEM_KEY}")
    private String ITEM_KEY;

    @Override
    public EasyUIResult findContentAll(Long contentCategoryId) {

        /**
         * 判断有无缓存
         */
        String json = JedisUtil.get(contentCategoryId + ":" + CONTENT_KEY);
        if(StringUtils.isNotBlank(json)){
            EasyUIResult result = JsonUtils.jsonToPojo(json, EasyUIResult.class);
            System.out.println("缓存中取数据");
            return result;
        }

        List<TbContent> contents = contentMapper.findContentByCategoryId(contentCategoryId);
        EasyUIResult result = new EasyUIResult();
        result.setTotal((long) contents.size());
        result.setRows(contents);
        /**
         * 数据存入redis缓存
         */
        JedisUtil.set(contentCategoryId + ":" + CONTENT_KEY, JsonUtils.objectToJson(result));
        System.out.println("缓存中存数据");
        return result;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        contentMapper.addContent(tbContent);
        JedisUtil.del(tbContent.getCategoryId()+":"+CONTENT_KEY);
        return TaotaoResult.ok(tbContent);
    }

    @Override
    public List<TbContent> getContentAll(Long contentCategoryId) {

        String json = JedisUtil.get(contentCategoryId + ":" + ITEM_KEY);
        if(StringUtils.isNotBlank(json)){
            List<TbContent> contents = JsonUtils.jsonToList(json, TbContent.class);
            System.out.println("缓存中取数据");
            return contents;
        }
        List<TbContent> contents = contentMapper.findContentByCategoryId(contentCategoryId);
        /**
         * 数据存入redis缓存
         */
        JedisUtil.set(contentCategoryId + ":" + ITEM_KEY, JsonUtils.objectToJson(contents));
        System.out.println("缓存中存数据");
        return contents;
    }
}
