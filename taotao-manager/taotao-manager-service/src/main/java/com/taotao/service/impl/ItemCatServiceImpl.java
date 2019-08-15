package com.taotao.service.impl;

import com.taotao.common.pojo.ItemCat;
import com.taotao.common.pojo.ItemCatResult;
import com.taotao.common.utils.JedisUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import com.taotao.common.pojo.EasyUITreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUITreeNode> getCatList(Long id) {
        List<TbItemCat> tbItemCats = tbItemCatMapper.findItemCatByParentId(id);
        List<EasyUITreeNode> result = new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCats) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent() ? "closed" : "open");
            result.add(node);
        }
        return result;
    }

    @Override
    public ItemCatResult getItemCatAll(Long parentId) {
        ItemCatResult result = new ItemCatResult();
        result.setData(getItemCatList(parentId));
        return result;
    }

    private List getItemCatList(Long parentId) {
        List<TbItemCat> itemCatByParentId = tbItemCatMapper.findItemCatByParentId(parentId);
        List data = new ArrayList();
        int count = 0;
        for (TbItemCat tbItemCat : itemCatByParentId) {
            ItemCat itemCat = new ItemCat();
            if (tbItemCat.getIsParent()) {
                itemCat.setUrl("/products/" + tbItemCat.getId() + ".html");
                if (parentId == 0) {
                    //设置第一层目录结构
                    itemCat.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                } else {
                    //设置第二层目录结构
                    itemCat.setName(tbItemCat.getName());
                }
                itemCat.setItem(getItemCatList(tbItemCat.getId()));
                //迭代到最后一层
                data.add(itemCat);
                count ++;
                if(parentId == 0 && count >= 14){
                    break;
                }
            } else {
                //只有最后一层
                data.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
            }
        }
        return data;
    }
}
