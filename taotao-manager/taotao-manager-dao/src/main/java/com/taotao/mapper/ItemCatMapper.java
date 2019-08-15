package com.taotao.mapper;

import com.taotao.pojo.TbItemCat;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemCatMapper {

    @Select("SELECT * FROM tbitemcat WHERE parentId = #{id}")
    List<TbItemCat> findItemCatByParentId(Long id);

    @Select("SELECT * FROM tbitemcat WHERE id = #{id}")
    TbItemCat findItemCatById(Long id);
}
