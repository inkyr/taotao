package com.taotao.mapper;

import com.taotao.pojo.TbOrderItem;
import org.apache.ibatis.annotations.Insert;

public interface OrderItemMapper {
    @Insert("INSERT INTO tborderitem(id, itemId, orderId, num, title, price, totalFee, picPath) VALUE (#{id},#{itemId},#{orderId},#{num},#{title},#{price},#{totalFee},#{picPath})")
    void addOrderItem(TbOrderItem orderItem);
}