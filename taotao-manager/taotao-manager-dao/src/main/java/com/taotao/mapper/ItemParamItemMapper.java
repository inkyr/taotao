package com.taotao.mapper;

import com.taotao.pojo.TbItemParamItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface ItemParamItemMapper {

    @Insert("INSERT INTO tbitemparamitem (itemId, paramData, created, updated) VALUES (#{itemId}, #{paramData}, #{created}, #{updated})")
    int addItemParamItem(TbItemParamItem tbItemParamItem);

    @Select("SELECT * FROM tbitemparamitem WHERE itemId = #{itemId}")
    TbItemParamItem findItemParamByItemId(Long itemId);
}
