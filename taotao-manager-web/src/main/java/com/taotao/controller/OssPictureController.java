package com.taotao.controller;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.OssPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/pic")
public class OssPictureController {

    @Autowired
    private OssPictureService ossPictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult uploadPicture(MultipartFile uploadFile){
        try {
            byte[] bytes = uploadFile.getBytes();
            String name = uploadFile.getOriginalFilename();
            PictureResult pictureResult = ossPictureService.uploadFile(bytes, name);
            return pictureResult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
