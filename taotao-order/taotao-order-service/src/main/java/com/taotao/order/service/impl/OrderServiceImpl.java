package com.taotao.order.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JedisUtil;
import com.taotao.mapper.OrderItemMapper;
import com.taotao.mapper.OrderShippingMapper;
import com.taotao.order.dao.OrderMapper;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderShippingMapper orderShippingMapper;
    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_ID_BEGIN}")
    private String ORDER_ID_BEGIN;
    @Value("${ORDER_ITEM_ID_GEN_KEY}")
    private String ORDER_ITEM_ID_GEN_KEY;

    @Override
    public TaotaoResult createOrder(OrderInfo orderInfo) {
        if(!JedisUtil.exists(ORDER_GEN_KEY)){
            JedisUtil.set(ORDER_GEN_KEY,ORDER_ID_BEGIN);
        }
        //使用redis的自增长方法  100544  变成 100545
        String orderId = JedisUtil.incr(ORDER_GEN_KEY).toString();
        //设置订单id
        orderInfo.setOrderId(orderId);
        //设置邮寄费用
        orderInfo.setPostFee("0");
        //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        Date date = new Date();
        orderInfo.setCreateTime(date);
        orderInfo.setUpdateTime(date);
        // 3、向订单表插入数据。
        orderMapper.addOrderInfo(orderInfo);
        //得到订单表中的所有订单项
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        //订单明细 都是从页面传递过来的  商品id 商品图片 商品数量 商品金额
        for (TbOrderItem orderItem:orderItems) {
            //生成明细id
            Long orderItemId = JedisUtil.incr(ORDER_ITEM_ID_GEN_KEY);
            //设置本身的id
            orderItem.setId(orderItemId.toString());
            //设置订单的id
            orderItem.setOrderId(orderId);
            orderItemMapper.addOrderItem(orderItem);
        }
        //地址明细
        // 5、向订单物流表插入数据。
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.addOrderShipping(orderShipping);

        //页面想要看到 订单号
        return TaotaoResult.ok(orderId);
    }
}
