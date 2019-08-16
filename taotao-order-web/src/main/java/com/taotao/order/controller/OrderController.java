package com.taotao.order.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Value("${TT_CART}")
    private String TT_CART;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/order-cart")
    public String showOrder(HttpServletRequest request, Model model) {
        List<TbItem> itemList = getItemList(request);
        model.addAttribute("cartList", itemList);
        return "order-cart";
    }

    @RequestMapping(value = "/order/create",method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUserName());
        // 3、调用Service创建订单。
        TaotaoResult result = orderService.createOrder(orderInfo);
        //取订单号
        String orderId = result.getData().toString();
        // a)需要Service返回订单号
        request.setAttribute("orderId", orderId);
        request.setAttribute("payment", orderInfo.getPayment());
        // b)当前日期加三天。
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(3);
        request.setAttribute("date", dateTime.toString("yyyy-MM-dd"));
        return "success";
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
