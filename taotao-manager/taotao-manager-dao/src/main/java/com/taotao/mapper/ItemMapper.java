package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ItemMapper {

    @Select("SELECT * FROM tbitem WHERE id=#{itemId}")
    TbItem findItemById(Long itemId);

    @Select("SELECT * FROM tbitem")
    List<TbItem> findItemAll();

    @Delete("<script> DELETE from tbitem WHERE id IN <foreach collection = 'array' item='id' open='(' separator = ',' close=')'>#{id}</foreach> </script>")
    int delItems(Long[] ids);

    @Update("<script> UPDATE tbitem SET status=2 WHERE id IN <foreach collection = 'array' item = 'id' open = '(' separator = ',' close = ')'>#{id}</foreach> </script>")
    int downItem(Long[] ids);

    @Update("<script> UPDATE tbitem SET status=1 WHERE id IN <foreach collection = 'array' item = 'id' open = '(' separator = ',' close = ')'>#{id}</foreach> </script>")
    int upItem(Long[] ids);

    @Insert("INSERT INTO tbitem (id, title, sellPoint, price, num, barcode, image, cid, status, created, updated) VALUES (#{id}, #{title}, #{sellPoint}, #{price}, #{num}, #{barcode}, #{image}, #{cid}, #{status}, #{created}, #{updated})")
    int addItem(TbItem tbItem);

    @Select("SELECT * FROM tbitem WHERE id = #{itemId}")
    TbItem getItemById(Long itemId);

    @Select("SELECT * from tbitemdesc WHERE itemId = #{itemId}")
    TbItemDesc getItemDescById(Long itemId);

}
