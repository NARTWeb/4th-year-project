package com.nart.controller;

import com.nart.common.LogA;
import com.nart.pojo.friendReq;
import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.vo.PageVo;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: FriendController
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/31 14:40
 */
@RestController
@RequestMapping("friend")
@LogA
public class FriendController {

    @GetMapping("list")
    public Result showFriendList(@RequestBody PageVo page) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping("search/{input}")
    public Result searchFriend(@RequestBody PageVo page,
                               @PathVariable("input") String input) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @DeleteMapping("del/{friendId}")
    public Result delFriend(@PathVariable("friendId") String fid) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping("state/{friendId}/{state}")
    public Result changeFriendState(@PathVariable("friendId") String fid,
                                    @PathVariable("state") Integer state) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping("reqlist")
    public Result showReqList(@RequestBody PageVo page) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping("send")
    public Result sendFriendReq(@RequestBody friendReq reqInfo) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping("resp/{requestId}/{agree}")
    public Result respFriendReq(@PathVariable("requestId") String reqId,
                                @PathVariable("agree") Boolean agree) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping("searchNew/{input}")
    public Result showReqList(@RequestBody PageVo page,
                              @PathVariable("input") String input) {
        return Result.fail(ErrorCode.UNDEFINED);
    }
}
