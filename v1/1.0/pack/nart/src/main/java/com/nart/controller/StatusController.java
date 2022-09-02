package com.nart.controller;

import com.nart.common.LogA;
import com.nart.pojo.Status;
import com.nart.service.StatusService;
import com.nart.util.ErrorCode;
import com.nart.util.GsonFormatter;
import com.nart.util.Result;
import com.nart.vo.PageVo;
import com.nart.vo.StatusVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
@LogA
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("list/{type}/{uid}")
    public Result showStatusList(@PathVariable("type") String type,
                                 @PathVariable("uid") String uid,
                                 @RequestBody PageVo page) {
        /*if (type.equals("my")){
            if(uid.equals("")) {
                show current User's Status
            } else {
                show uid User's Status
            }
        } else {
            show all visible Status for current User
        }*/
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PutMapping("like/{statusId}/{like}")
    public Result likeStatus(@PathVariable("statusId") String statusId,
                             @PathVariable("like") Boolean like) {
        boolean b = statusService.likeStatus(statusId, like);
        if(b) {
            return Result.success(null);
        } else {
            return Result.fail(ErrorCode.LIKE_STATUS_ERROR);
        }
    }

    @PostMapping("post")
    public Result postStatus(@RequestBody StatusVo statusInfo) {
        Status status = new Status();
        status.setSenderId(statusInfo.getUid());
        status.setText(statusInfo.getMsg());
        status.setPics(GsonFormatter.toJsonString(statusInfo.getPics()));
        status.setLikes(0);
        status.setCreateDate(new Date().getTime());
        boolean b = statusService.postStatus(status);
        if(b) {
            return Result.success(null);
        } else {
            return Result.fail(ErrorCode.POST_STATUS_ERROR);
        }
    }

    @DeleteMapping("del/{statusId}")
    public Result delStatus(@PathVariable("statusId") String statusId) {
        boolean b = statusService.delStatus(statusId);
        if(b) {
            return Result.success(null);
        } else {
            return Result.fail(ErrorCode.DEL_STATUS_ERROR);
        }
    }
}
