package com.nart.controller;

import com.nart.common.LogA;
import com.nart.pojo.Comment;
import com.nart.service.CommentService;
import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.util.UserThreadLocal;
import com.nart.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@LogA
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("list/{statusId}")
    public Result showCommentList(@PathVariable String statusId) {
        List<Comment> Comments = commentService.showCommentList(statusId);
        if(Comments == null) {
            return Result.fail(ErrorCode.SHOW_COMMENT_LIST_ERROR);
        }
        return Result.success(Comments);
    }

    @PostMapping("/post")
    public Result postComment(@RequestBody CommentVo cInfo) {
        boolean b = commentService.postComment(cInfo.getStatusId(), cInfo.getMsg(), UserThreadLocal.get().getId());
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.POST_COMMENT_ERROR);
    }
}
