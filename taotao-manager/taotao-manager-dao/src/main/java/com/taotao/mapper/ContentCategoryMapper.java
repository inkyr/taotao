package com.taotao.mapper;

import com.taotao.pojo.TbContentCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ContentCategoryMapper {

    @Select("SELECT * FROM tbcontentcategory WHERE parentId=#{id}")
    List<TbContentCategory> findContentCategoryByParentId(Long parentId);

    @Select("SELECT * from tbcontentcategory WHERE id = #{parentId}")
    TbContentCategory findContentCategoryById(Long parentId);

    @Update("UPDATE tbcontentcategory SET isParent = #{isParent} WHERE id = #{id}")
    int updateContentCategory(TbContentCategory category);

    @Insert("INSERT INTO tbcontentcategory (parentId, name, status, sortOrder, isParent, created, updated) values (#{parentId}, #{name}, #{status}, #{sortOrder}, #{isParent}, #{created}, #{updated})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Long addContentCategory(TbContentCategory category);

    @Update("UPDATE tbcontentcategory SET name=#{name} WHERE id=#{id}")
    void updateContentCategoryName(@Param("id") Long id, @Param("name") String name);

    @Delete("DELETE FROM tbcontentcategory WHERE id = #{id}")
    void deleteContentCategory(Long id);
}
