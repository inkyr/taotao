package com.taotao.controller;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIResult getItemList(Integer page, Integer rows){
        EasyUIResult result = itemService.findItem(page, rows);
        return result;
    }

    /**
     * 删除商品
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult delItem(Integer[] ids){
        TaotaoResult result= itemService.delItems(ids);
        return result;
    }

    /**
     * 下架商品
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public TaotaoResult downItem(Integer[] ids){
        TaotaoResult result= itemService.updateDownItem(ids);
        return result;

    }

    /**
     * 上架商品
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public TaotaoResult upItem(Integer[] ids){
        TaotaoResult result= itemService.updateUpItem(ids);
        return result;
    }

    /**
     * 添加商品信息
     * @param tbItem
     * @param tbItemDesc
     * @return
     */
    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult addItem(TbItem tbItem, TbItemDesc tbItemDesc){
        TaotaoResult result = itemService.addItem(tbItem, tbItemDesc);
        return result;
    }
}


