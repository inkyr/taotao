package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.ContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        List<TbContentCategory> contentCategories = contentCategoryMapper.findContentCategoryByParentId(parentId);
        List<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbContentCategory contentCategory : contentCategories) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public TaotaoResult addContentCategory(Long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        Date date = new Date();
        contentCategory.setIsParent(false);
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);
        contentCategory.setCreated(date);
        contentCategory.setUpdated(date);

        contentCategoryMapper.addContentCategory(contentCategory);

        TbContentCategory category = contentCategoryMapper.findContentCategoryById(parentId);
        if (!category.getIsParent()) {
            category.setIsParent(true);
            contentCategoryMapper.updateContentCategory(category);
        }
        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public void updateContentCategory(Long id, String name) {
        contentCategoryMapper.updateContentCategoryName(id, name);
    }


    ////////////////////////////////////////////////////////////////

    //递归删除方法一
    @Override
    public void deleteContentCategory(Long id) {
        //通过查询有无子级目录递归实现删除
        List<TbContentCategory> contentCategoryList = contentCategoryMapper.findContentCategoryByParentId(id);
        if (contentCategoryList.size() > 0) {
            for (TbContentCategory contentCategory : contentCategoryList) {
                deleteContentCategory(contentCategory.getId());
            }
        } else {
            contentCategoryMapper.deleteContentCategory(id);
        }
    }

    //递归删除方法二
    public void delete(TbContentCategory tbContentCategory) {
        if (tbContentCategory.getIsParent()) {
            List<TbContentCategory> contentCategoryByParentId = contentCategoryMapper.findContentCategoryByParentId(tbContentCategory.getId());
            for (TbContentCategory tbContentCategoryCopyT : contentCategoryByParentId) {
                delete(tbContentCategoryCopyT);
            }
        } else {
            contentCategoryMapper.deleteContentCategory(tbContentCategory.getId());
        }
    }


    //递归调用修改方法，同时修改父级状态，此处使用方法一，注释方法二
    @Override
    public void deleteAndChangeState(Long id) {
        //递归删除目录
        deleteContentCategory(id);
        //修改父级目录状态
        TbContentCategory category = contentCategoryMapper.findContentCategoryById(id);


        //递归方法二实现删除
        //delete(category);


        category.setIsParent(false);
        category.setId(category.getParentId());
        contentCategoryMapper.updateContentCategory(category);
    }

}
