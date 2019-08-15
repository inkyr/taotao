package com.taotao.order.controller;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Value("${TT_CART}")
    private String TT_CART;

    @RequestMapping("/order/order-cart")
    public String showOrder(HttpServletRequest request, Model model) {
        List<TbItem> itemList = getItemList(request);
        model.addAttribute("cartList", itemList);
        return "order-cart";
    }

    //调用这个方法就可以从cookie中获取一个集合对象 里面可能有商品信息也可能没有
    private List<TbItem> getItemList(HttpServletRequest request) {
        //cookie(key=TT_CART,value=商品json数据)
        String json = CookieUtils.getCookieValue(request, TT_CART, true);
        if (StringUtils.isNotBlank(json)) {
            List<TbItem> reuslt = JsonUtils.jsonToList(json, TbItem.class);
            return reuslt;
        }
        return new ArrayList<>();
    }
}
