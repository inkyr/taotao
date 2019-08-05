package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/category/list")
    @ResponseBody
    public List<EasyUITreeNode> showContentCategory(@RequestParam(value = "id", defaultValue = "0") Long parentId){
        List<EasyUITreeNode> result = contentCategoryService.getContentCategoryList(parentId);
        return result;
    }

    @RequestMapping("/category/create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name){
        TaotaoResult result = contentCategoryService.addContentCategory(parentId, name);

        return result;
    }

    @RequestMapping("/category/update")
    @ResponseBody
    public void updateContentCategory(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name){
        contentCategoryService.updateContentCategory(id, name);
    }
}
