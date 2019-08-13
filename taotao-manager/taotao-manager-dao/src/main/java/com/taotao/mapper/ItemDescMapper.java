package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.*;

public interface ItemDescMapper {

    @Insert("INSERT INTO tbitemdesc (itemId, itemDesc, created, updated) VALUES (#{itemId}, #{itemDesc}, #{created}, #{updated})")
    int addItemDesc(TbItemDesc tbItemDesc);

    @Select("SELECT * FROM tbitemdesc WHERE itemId=#{itemId}")
    TbItemDesc findItemDescById(Long itemId);
}
