package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
    TaotaoResult checkData(String param, Integer type);
    TaotaoResult createUser(TbUser tbUser);
    TaotaoResult login(String userName, String passWord);
    TaotaoResult getUserByToken(String token);
    TaotaoResult logout(String token);

}
