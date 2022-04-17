package com.example.demo.controller;

import com.example.demo.handler.NoAuth;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Api(tags = "图片上传")
public class UpFileController {
    @PostMapping("/uploadFile")
    @NoAuth
    public String uploadFile(@RequestParam("file") MultipartFile file){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        String name = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        name=dateString+name;
        File newFile = new File("/image/"+name);
        try {
            file.transferTo(newFile);
            System.out.println("文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件上传失败");
        }
        name="https://alexmisko.top/static/"+name;
        return name;
    }
}
