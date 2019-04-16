package com.qf.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/img")
public class ImgController {

    private static final String UPLOAD_PATH = "D:\\imgs";

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file){

        System.out.println("有图片开始上传了"+file.getOriginalFilename());

        //上传到FastFDS中，暂时上传到本地
        //获得最后一个.的下标  xxxxx.jpg
        int index = file.getOriginalFilename().lastIndexOf(".");

        String houzui = file.getOriginalFilename().substring(index + 1);

        System.out.println("截取到的后缀"+houzui);

        //上传到FastFDS中
        try {
            //uploadImageAndCrtThumbImage:上传并生成缩略图片
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(),
                    file.getSize(),
                    houzui,
                    null);
            //获取上传到FastDFS中的图片访问路径

            String storeUrl = storePath.getFullPath();
            System.out.println("获取上传到FastDFS中的路径"+storeUrl);

            return "{\"uploadPath\":\"" + storeUrl + "\"}";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
