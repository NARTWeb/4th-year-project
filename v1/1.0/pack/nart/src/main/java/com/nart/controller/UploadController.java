package com.nart.controller;

import com.nart.util.ErrorCode;
import com.nart.util.ImgtuUtil;
import com.nart.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: UploadController
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/31 15:27
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String suffix = StringUtils.substringAfterLast(name, ".");
        String fileName = UUID.randomUUID().toString() + "." + suffix;
        String s = ImgtuUtil.uploadPic(file.getBytes(), fileName);
        if(StringUtils.isNotBlank(s)) {
            return Result.success(s);
        }
        return Result.fail(ErrorCode.UPLOAD_ERROR);

    }
}
