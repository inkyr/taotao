package com.taotao.search.controller;

import com.taotao.common.pojo.SearchResult;
import com.taotao.pojo.TbItemCat;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductsController {
    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/products/{categoryId}")
    public String searchProducts(@PathVariable("categoryId")Long categoryId, @RequestParam(defaultValue="1")Integer page, Model model){
        TbItemCat itemCat = searchItemService.getItemCatById(categoryId);
        SearchResult result = searchItemService.searchProducts(itemCat.getName(), page, ITEM_ROWS);

        model.addAttribute("query", itemCat.getName());
        model.addAttribute("totalPages", result.getPageCount());
        model.addAttribute("itemList", result.getItemList());
        model.addAttribute("page", page);

        return "search";
    }
}
