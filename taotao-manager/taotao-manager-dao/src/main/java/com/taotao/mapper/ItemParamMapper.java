package com.taotao.mapper;

import com.taotao.pojo.TbItemParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemParamMapper {

    @Select("SELECT * FROM tbitemparam WHERE  itemCatId = #{itemCatId}")
    TbItemParam findItemParamByCatId(Long ItemCatId);

    @Insert("INSERT INTO tbitemparam (itemCatId, paramData, created, updated) VALUES (#{itemCatId}, #{paramData}, #{created}, #{updated})")
    void addItemParam(TbItemParam tbItemParam);

    @Select("SELECT * FROM tbitemparam")
    List<TbItemParam> findParamAll();

    @Delete("<script> DELETE from tbitemparam WHERE id IN <foreach collection = 'array' item='ids' open='(' separator = ',' close=')'>#{ids}</foreach></script>")
    int deleteParam(Long[] ids);
}
