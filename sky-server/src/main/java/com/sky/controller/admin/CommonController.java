package com.sky.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sky.result.Result;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    /**
     * 上传图片
     * 
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("上传图片：{}", file);
        String originalFilename = file.getOriginalFilename();
        String suffix = ".jpg";
        if (originalFilename != null) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID() + suffix;
        String imgUrl = "http://localhost/media/" + fileName;
        try {
//            file.transferTo(new java.io.File("D:\\Variable\\nginx-1.24.0\\media\\" + fileName));
            File dest = new File("/Users/apple/desktop/sky/media/" + fileName);
            file.transferTo(dest);
            return Result.success(imgUrl);
        } catch (Exception e) {
            log.error("上传图片失败：{}", e);
            return Result.error("上传图片失败");
        }
    }
}
//Exception e
//IllegalStateException | IOException e