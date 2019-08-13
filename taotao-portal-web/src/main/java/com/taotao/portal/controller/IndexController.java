package com.taotao.portal.controller;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.ItemCatResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.Ad1Node;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class IndexController {

    @Value("${AD1_CID}")
    private Long AD1_CID;
    @Value("${AD1_HEIGHT}")
    private Integer AD1_HEIGHT;
    @Value("${AD1_WIDTH}")
    private Integer AD1_WIDTH;
    @Value("${AD1_HEIGHT_B}")
    private Integer AD1_HEIGHT_B;
    @Value("${AD1_WIDTH_B}")
    private Integer AD1_WIDTH_B;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        List<TbContent> result = contentService.getContentAll(AD1_CID);

        List<Ad1Node> ad1List = new ArrayList<>();
        for (TbContent tbContent : result) {
            Ad1Node node = new Ad1Node();
            node.setAlt(tbContent.getTitle());
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            node.setHref(tbContent.getUrl());
            node.setSrc(tbContent.getPic());
            node.setSrcB(tbContent.getPic2());
            //添加到列表
            ad1List.add(node);
        }

        model.addAttribute("ad1", JsonUtils.objectToJson(ad1List));
        return "index";
    }

    @RequestMapping("/item/cat/itemcat/all")
    @ResponseBody
    public String queryAll(String callback) {
        ItemCatResult result = itemCatService.getItemCatAll(0L);
        String str = callback + "(" + JsonUtils.objectToJson(result) + ");";
        return str;
    }
}
