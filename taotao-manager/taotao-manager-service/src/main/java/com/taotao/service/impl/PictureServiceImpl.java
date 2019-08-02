package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FILI_UPLOAD_PATH}")
    private String FILI_UPLOAD_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public PictureResult uploadFile(byte[] bytes, String name) {
        //获取图片的名称
        PictureResult pictureResult = new PictureResult();
        String newName = IDUtils.genImageName() + name.substring(name.lastIndexOf("."));
        String filepath = new DateTime().toString("yyyy/MM/dd");
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        boolean b = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FILI_UPLOAD_PATH, filepath, newName, bis);
        if(b){
            pictureResult.setError(0);
            pictureResult.setUrl(IMAGE_BASE_URL + "/" + filepath + "/" + newName);
            return pictureResult;
        }
        pictureResult.setError(1);
        pictureResult.setMessage("上传错误");
        return pictureResult;
    }

}
