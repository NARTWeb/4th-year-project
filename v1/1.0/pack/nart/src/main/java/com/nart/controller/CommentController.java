package com.nart.controller;

import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.vo.CommentVo;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: CommentController
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/31 15:10
 */
@RestController
@RequestMapping("comment")
public class CommentController {


    @GetMapping("list/{statusId}")
    public Result showCommentList(@PathVariable String statusId) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PostMapping("/post")
    public Result postComment(@RequestBody CommentVo cInfo) {
        return Result.fail(ErrorCode.UNDEFINED);
    }
}
