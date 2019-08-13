package com.taotao.item.controller;

import com.taotao.item.pojo.Item;
import com.taotao.mapper.ItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable("itemId") Long itemId, Model model){
        TbItem tbItem = itemService.getItemById(itemId);
        Item item = new Item(tbItem);
        model.addAttribute("item", item);
        return "item";
    }

    @RequestMapping(value = "/item/desc/{itemId}")
    @ResponseBody
    public String showItemDesc(@PathVariable("itemId") Long itemId, Model model) {
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        String desc = itemDesc.getItemDesc();
        return desc;
    }

    @RequestMapping("/item/param/{itemId}")
    @ResponseBody
    public String showItemDesc(@PathVariable("itemId") Long itemId) {
        String result = itemParamService.getItemParamByItemId(itemId);
        return result;
    }
}
