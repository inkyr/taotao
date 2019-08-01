package com.taotao.controller;

import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    private ItemService itemService;

    /**
     * 主页
     * @return
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    /**
     * 转发菜单页面
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

    /**
     * 转发商品编辑页面
     * @return
     */
    @RequestMapping("/rest/page/item-edit")
    public String showEdit(){
        return "item-edit";
    }

    /**
     * 分类页面
     * @return
     */
    @RequestMapping("/rest/cat/list")
    public String showList(){
        return "item-edit";
    }
}
