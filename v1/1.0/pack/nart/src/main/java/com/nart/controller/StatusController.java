package com.nart.controller;

import com.nart.pojo.user;
import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.vo.PageVo;
import com.nart.vo.StatusVo;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: StatusController
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/31 14:56
 */
@RestController
@RequestMapping("status")
public class StatusController {


    @GetMapping("list/{type}/{uid}")
    public Result showStatusList(@PathVariable("type") String type,
                                 @PathVariable("uid") String uid,
                                 @RequestBody PageVo page) {
        /*if (type.equals("my")){
            if(uid.equals("")) {
                show current user's status
            }
        } else {
            show all visible status for current user
        }*/
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PutMapping("like/{statusId}/{like}")
    public Result likeStatus(@PathVariable("statusId") String statusId,
                             @PathVariable("like") Boolean like) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PostMapping("post")
    public Result postStatus(@RequestBody StatusVo statusInfo) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @DeleteMapping("del/{statusId}")
    public Result postStatus(@PathVariable("statusId") String statusId) {
        return Result.fail(ErrorCode.UNDEFINED);
    }
}
