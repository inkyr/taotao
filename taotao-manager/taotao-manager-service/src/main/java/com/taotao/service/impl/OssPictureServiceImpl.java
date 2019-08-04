package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.OssUtil;
import com.taotao.service.OssPictureService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class OssPictureServiceImpl implements OssPictureService {
    @Override
    public PictureResult uploadFile(byte[] bytes, String name) {
        PictureResult pictureResult = new PictureResult();
        String newName = IDUtils.genImageName() + name.substring(name.lastIndexOf("."));
        String filepath = new DateTime().toString("yyyy/MM/dd");

        boolean b = OssUtil.uploadOssFile(bytes, newName, filepath);
        if(b){
            pictureResult.setError(0);
            pictureResult.setUrl("http://keepin.oss-cn-hangzhou.aliyuncs.com/taotao/" + filepath + "/" + newName);
            return pictureResult;
        }
        pictureResult.setError(1);
        pictureResult.setMessage("上传错误");
        return pictureResult;
    }
}
