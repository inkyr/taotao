package com.taotao.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ftp上传下载工具类
 */
public class OssUtil {
    //文件上传
    public static boolean uploadOssFile(byte[] content, String fileName ,String filePath) {
        Boolean result = false;
        String accessKeyId = "LTAI9snmDW8ce1Pl";      // 请填写您的AccessKeyId。
        String accessKeySecret = "UIlvV6liYBio0EOFXBifkLBTcIkmZe"; // 请填写您的AccessKeySecret。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com"; // 请填写您的 endpoint。
        String bucket = "keepin";                    // 请填写您的 bucketname 。
        fileName = "taotao"+"/" +filePath+"/"+fileName;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucket, fileName, new ByteArrayInputStream(content));
        result = true;
        return result;
    }
}