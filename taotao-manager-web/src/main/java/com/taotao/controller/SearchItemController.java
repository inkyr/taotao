package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class SearchItemController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("importall")
    @ResponseBody
    public TaotaoResult importAllItens(){
        TaotaoResult result = searchItemService.importAllItems();
        return result;
    }

}
