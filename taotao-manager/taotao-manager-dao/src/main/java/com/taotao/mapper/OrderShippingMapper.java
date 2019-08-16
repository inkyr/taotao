package com.taotao.mapper;


import com.taotao.pojo.TbOrderShipping;
import org.apache.ibatis.annotations.Insert;

public interface OrderShippingMapper {
    @Insert("INSERT INTO tbordershipping(orderId, receiverName, receiverPhone, receiverMobile, receiverState, receiverCity, receiverDistrict, receiverAddress, receiverZip, created, updated) VALUE (#{orderId},#{receiverName},#{receiverPhone},#{receiverMobile},#{receiverState},#{receiverCity},#{receiverDistrict},#{receiverAddress},#{receiverZip},#{created},#{updated})")
    void addOrderShipping(TbOrderShipping orderShipping);
}