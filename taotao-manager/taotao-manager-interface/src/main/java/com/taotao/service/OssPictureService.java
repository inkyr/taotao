package com.taotao.service;

import com.taotao.common.pojo.PictureResult;

public interface OssPictureService {

    PictureResult uploadFile(byte[] bytes, String name);
}
