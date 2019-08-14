package com.taotao.mapper;

import com.taotao.pojo.TbUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("SELECT * FROM tbuser WHERE userName = #{username}")
    TbUser checkUserName(String username);

    @Select("SELECT * FROM tbuser WHERE phone = #{phone}")
    TbUser checkPhone(String phone);

    @Select("SELECT * FROM tbuser WHERE email = #{email}")
    TbUser checkEmail(String email);

    @Insert("INSERT INTO tbuser (userName, passWord, phone, email, created, updated) VALUES (#{userName}, #{passWord}, #{phone}, #{email}, #{created}, #{updated})")
    void addUser(TbUser tbUser);

    @Select("SELECT * FROM tbuser WHERE userName = #{userName} AND passWord = #{passWord}")
    TbUser findUserByUserNameAndPassWord(@Param("userName") String userName, @Param("passWord") String md5DigestAsHex);
}
