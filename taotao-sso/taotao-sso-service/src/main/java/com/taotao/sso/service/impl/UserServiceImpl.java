package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JedisUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.UserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //用户注册参数校验
    @Override
    public TaotaoResult checkData(String param, Integer type) {

        TbUser tbUser = null;
        if (type == 1) {
            tbUser = userMapper.checkUserName(param);
            if (tbUser != null) {
                return TaotaoResult.ok(false);
            }
        } else if (type == 2) {
            tbUser = userMapper.checkPhone(param);
            if (tbUser != null) {
                return TaotaoResult.ok(false);
            }
        } else if (type == 3) {
            tbUser = userMapper.checkEmail(param);
            if (tbUser != null) {
                return TaotaoResult.ok(false);
            }
        } else {
            return TaotaoResult.build(400, "非法的参数");
        }

        return TaotaoResult.ok(true);
    }

    @Override
    public TaotaoResult createUser(TbUser tbUser) {

        TaotaoResult result = null;
        if (StringUtils.isBlank(tbUser.getUserName())) {
            return TaotaoResult.build(400, "用户名不能为空");
        }
        if (StringUtils.isBlank(tbUser.getPassWord())) {
            return TaotaoResult.build(400, "密码不能为空");
        }
        if (StringUtils.isBlank(tbUser.getPhone())) {
            return TaotaoResult.build(400, "手机号不能为空");
        }
        if (StringUtils.isBlank(tbUser.getEmail())) {
            return TaotaoResult.build(400, "邮箱不能为空");
        }

        result = checkData(tbUser.getUserName(), 1);
        if (!(boolean) result.getData()) {
            return TaotaoResult.build(400, "用户名重复");
        }
        result = checkData(tbUser.getPhone(), 2);
        if (!(boolean) result.getData()) {
            return TaotaoResult.build(400, "手机号重复");
        }
        result = checkData(tbUser.getEmail(), 3);
        if (!(boolean) result.getData()) {
            return TaotaoResult.build(400, "邮箱重复");
        }

        Date date = new Date();
        tbUser.setCreated(date);
        tbUser.setUpdated(date);
        tbUser.setPassWord(DigestUtils.md5DigestAsHex(tbUser.getPassWord().getBytes()));
        userMapper.addUser(tbUser);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult login(String userName, String passWord) {
        if (StringUtils.isBlank(userName)) {
            return TaotaoResult.build(400, "用户名不能为空");
        }
        if (StringUtils.isBlank(passWord)) {
            return TaotaoResult.build(400, "密码不能为空");
        }
        TbUser tbUser = userMapper.findUserByUserNameAndPassWord(userName, DigestUtils.md5DigestAsHex(passWord.getBytes()));
        if(tbUser == null){
            return TaotaoResult.build(400, "账号或密码错误");
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        System.out.println(token);
        tbUser.setPassWord(null);
        JedisUtil.set("USER_INFO:"+token, JsonUtils.objectToJson(tbUser));
        JedisUtil.expire("USER_INFO:"+token, 1800);
        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) {
        String json = JedisUtil.get("USER_INFO:" + token);
        if(StringUtils.isBlank(json)){
            return TaotaoResult.build(400, "用户登录已过期，请重新登录");
        }
        JedisUtil.expire("USER_INFO:"+token, 1800);
        TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
        return TaotaoResult.ok(tbUser);
    }

    @Override
    public TaotaoResult logout(String token) {
        Long del = JedisUtil.del("USER_INFO:" + token);
        return TaotaoResult.ok(del);
    }
}
