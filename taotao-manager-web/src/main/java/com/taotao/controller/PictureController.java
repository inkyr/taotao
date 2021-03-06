package com.taotao.controller;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 该方法用于向本地ftp图片服务器上传图片，使用此方法请务必关闭阿里云图片服务器连接
 */
@Controller
@RequestMapping("/测试连接，暂时关闭")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult uploadPicture(MultipartFile uploadFile){
        try {
            byte[] bytes = uploadFile.getBytes();
            String name = uploadFile.getOriginalFilename();
            PictureResult pictureResult = pictureService.uploadFile(bytes,name);
            return pictureResult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
