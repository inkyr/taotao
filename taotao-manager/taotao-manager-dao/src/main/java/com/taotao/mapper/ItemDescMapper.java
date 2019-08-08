package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.*;

public interface ItemDescMapper {

    @Insert("INSERT INTO tbitemdesc (itemId, itemDesc, created, updated) VALUES (#{itemId}, #{desc}, #{created}, #{updated})")
    int addItemDesc(TbItemDesc tbItemDesc);

    @Select("SELECT * FROM tbitemdesc WHERE itemId=#{itemId}")
    @Results({
            @Result(property = "desc", column = "itemDesc")
    })
    TbItemDesc findItemDescById(Long itemId);
}
