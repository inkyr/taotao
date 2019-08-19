package com.taotao.mapper;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ContentMapper {

    @Select("SELECT * FROM tbcontent WHERE categoryId = #{contentCategoryId}")
    List<TbContent> findContentByCategoryId(Long contentCategoryId);

    @Insert("INSERT INTO tbcontent (categoryId, title, subTitle, titleDesc, url, pic, pic2, content, created, updated) VALUES (#{categoryId}, #{title}, #{subTitle}, #{titleDesc}, #{url}, #{pic}, #{pic2}, #{content}, #{created}, #{updated})")
    void addContent(TbContent tbContent);

    @Update("UPDATE tbcontent SET categoryId=#{categoryId}, title=#{title}, subTitle=#{subTitle}, titleDesc=#{titleDesc}, url=#{url}, pic=#{pic}, pic2=#{pic2}, content=#{content}, updated=#{updated} WHERE id=#{id}")
    int updateContent(TbContent tbContent);
}
