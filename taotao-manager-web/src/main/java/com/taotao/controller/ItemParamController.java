package com.taotao.controller;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ItemParamItemMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult showItemParam(@PathVariable("itemCatId") Long itemCatId) {
        TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
        return result;
    }

    @RequestMapping("/save/{cid}")
    public TaotaoResult addItemParam(@PathVariable("cid") Long cid, String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setParamData(paramData);
        tbItemParam.setItemCatId(cid);
        TaotaoResult result = itemParamService.addItemParam(tbItemParam);
        return result;
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIResult getParamList(Integer page, Integer rows) {
        EasyUIResult result = itemParamService.findParamList(page, rows);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteParam(Long[] ids) {
        TaotaoResult result = itemParamService.deleteParam(ids);
        return result;
    }
}
