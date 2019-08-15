package com.taotao.cart.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private ItemService itemService;
    @Value("${TT_CART}")
    private String TT_CART;
    @Value("${CART_EXPIRE}")
    private Integer CART_EXPIRE;
    @RequestMapping("/cart/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model){
        List<TbItem> itemList = getItemList(request);
        model.addAttribute("cartList",itemList);
        return "cart";
    }
    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response){
        List<TbItem> itemList = getItemList(request);
        boolean flag = false;
        for (TbItem tbItem:itemList) {
            if(itemId == tbItem.getId().longValue()){
                //走这个逻辑 那么 一定是 设置了数量
                tbItem.setNum(tbItem.getNum()+num);
                flag = true;
                break;
            }
        }
        //如果为false 那么认为  是第一次添加  不管是 没有找到 还是 cookie里面没有
        if(!flag){ //只有循环里面的if条件没有走 才能走这里的if条件
            TbItem item = itemService.getItemById(itemId);
            String[] images = item.getImage().split(",");
            item.setImage(images[0]);
            //设置购买商品数量
            item.setNum(num);
            // 5、把商品添加到购车列表。
            itemList.add(item);
        }
        //最终走到这里  那么 list集合 一定有值了
        CookieUtils.setCookie(request,response,TT_CART,JsonUtils.objectToJson(itemList),CART_EXPIRE,true);
        return "cartSuccess";
    }

    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public TaotaoResult changeCartNum(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request, HttpServletResponse response){
        List<TbItem> itemList = getItemList(request);
        for (TbItem tbItem:itemList) {
            if(itemId == tbItem.getId().longValue()){
                //走这个逻辑 那么 一定是 设置了数量
                tbItem.setNum(num);
                break;
            }
        }
        //最终走到这里  那么 list集合 一定有值了
        CookieUtils.setCookie(request,response,TT_CART,JsonUtils.objectToJson(itemList),CART_EXPIRE,true);
        return TaotaoResult.ok();
    }



    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId,HttpServletResponse response,HttpServletRequest request) {
        List<TbItem> itemList = getItemList(request);
        for (TbItem tbItem : itemList) {
            if (itemId == tbItem.getId().longValue()) {
                itemList.remove(tbItem);
                break;
            }
        }
        //最终走到这里  那么 list集合 一定有值了
        CookieUtils.setCookie(request,response,TT_CART,JsonUtils.objectToJson(itemList),CART_EXPIRE,true);
        //意味着 商品真的值删除了一个  但是页面没有ajax刷新操作 所有拿不到cookie里面的商品 所以没有展示
        return "redirect:/cart/cart.html";
    }


    //调用这个方法就可以从cookie中获取一个集合对象 里面可能有商品信息也可能没有
    private List<TbItem> getItemList(HttpServletRequest request){
        //cookie(key=TT_CART,value=商品json数据)
        String json = CookieUtils.getCookieValue(request, TT_CART, true);
        if(StringUtils.isNotBlank(json)){
            List<TbItem> reuslt = JsonUtils.jsonToList(json, TbItem.class);
            return reuslt;
        }
        return new ArrayList<>();
    }
}
