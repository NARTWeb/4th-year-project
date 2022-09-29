package com.nart.controller;

import com.nart.common.LogA;
import com.nart.util.ErrorCode;
import com.nart.util.FakeDataGenerator;
import com.nart.util.upload.ImgtuUtil;
import com.nart.util.Result;
import com.nart.service.LoadDataInDataBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@LogA
public class UploadController {

    @Autowired
    private LoadDataInDataBase ld;

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


    @GetMapping("test")
    public Result test() {
        FakeDataGenerator fakeDataGenerator = new FakeDataGenerator();
        fakeDataGenerator.generateTestData(1);
        return Result.success("yes");
    }


}
