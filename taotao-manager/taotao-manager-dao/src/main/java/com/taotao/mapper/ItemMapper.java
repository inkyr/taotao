package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ItemMapper {

    @Select("SELECT * FROM tbitem WHERE id=#{itemId}")
    TbItem findItemById(Long itemId);

    @Select("SELECT * FROM tbitem")
    List<TbItem> findItemAll();

    @Delete("<script> DELETE from tbitem WHERE id IN <foreach collection = 'array' item='id' open='(' separator = ',' close=')'>#{id}</foreach> </script>")
    int delItems(Integer[] ids);

    @Update("<script> UPDATE tbitem SET status=2 WHERE id IN <foreach collection = 'array' item = 'id' open = '(' separator = ',' close = ')'>#{id}</foreach> </script>")
    int downItem(Integer[] ids);

    @Update("<script> UPDATE tbitem SET status=1 WHERE id IN <foreach collection = 'array' item = 'id' open = '(' separator = ',' close = ')'>#{id}</foreach> </script>")
    int upItem(Integer[] ids);

    @Insert("INSERT INTO tbitem (id, title, sellPoint, price, num, barcode, image, cid, status, created, updated) VALUES (#{id}, #{title}, #{sellPoint}, #{price}, #{num}, #{barcode}, #{image}, #{cid}, #{status}, #{created}, #{updated})")
    int addItem(TbItem tbItem);
}
