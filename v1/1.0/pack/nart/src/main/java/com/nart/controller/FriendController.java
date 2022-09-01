package com.nart.controller;

import com.nart.common.LogA;
import com.nart.pojo.friend;
import com.nart.pojo.friendReq;
import com.nart.pojo.user;
import com.nart.service.FriendService;
import com.nart.util.ErrorCode;
import com.nart.util.GsonFormatter;
import com.nart.util.Result;
import com.nart.util.UserThreadLocal;
import com.nart.vo.PageVo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private FriendService friendService;

    @GetMapping("list")
    public Result showFriendList(@RequestBody PageVo page) {
        List<friend> friends = friendService.showFriendList(
                page.toIPage(friend.class), UserThreadLocal.get().getId());
        if(friends == null) {
            return Result.fail(ErrorCode.SHOW_FRIEND_LIST_ERROR);
        }
        return Result.success(friends);
    }

    @GetMapping("search/{input}")
    public Result searchFriend(@RequestBody PageVo page,
                               @PathVariable("input") String input) {
        List<user> users = friendService.searchFriend(input, page.toIPage(friend.class));
        if(users == null) {
            return Result.success(ErrorCode.SEARCH_FRIEND_ERROR);
        }
        return Result.success(users);
    }

    @DeleteMapping("del/{friendId}")
    public Result delFriend(@PathVariable("friendId") String fid) {
        boolean b = friendService.delFriend(fid, UserThreadLocal.get().getId());
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.DEL_FRIEND_ERROR);
    }

    @GetMapping("state/{friendId}/{state}")
    public Result changeFriendState(@PathVariable("friendId") String fid,
                                    @PathVariable("state") Integer state) {
        boolean b = friendService.changeFriendState(fid, UserThreadLocal.get().getId(), state);
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.CHANGE_FRIEND_STATE_ERROR);
    }

    @GetMapping("reqlist")
    public Result showReqList(@RequestBody PageVo page) {
        List<friendReq> friendReqs = friendService.showReqList(
                page.toIPage(friendReq.class), UserThreadLocal.get().getId());
        if(friendReqs == null) {
            return Result.fail(ErrorCode.SHOW_FRIEND_REQS_ERROR);
        }
        return Result.success(friendReqs);
    }

    @GetMapping("send")
    public Result sendFriendReq(@RequestBody friendReq reqInfo) {
        boolean b = friendService.sendFriendReq(
                reqInfo.getReceiverId(), UserThreadLocal.get().getId(), reqInfo.getMsg());
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.SEND_FRIEND_REQ_ERROR);
    }

    @GetMapping("resp/{requestId}/{agree}")
    public Result respFriendReq(@PathVariable("requestId") String reqId,
                                @PathVariable("agree") Boolean agree) {
        boolean b = friendService.respFriendReq(reqId, agree);
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.RESP_FRIEND_REQ_ERROR);
    }

    @GetMapping("searchNew/{input}")
    public Result searchNew(@RequestBody PageVo page,
                              @PathVariable("input") String input) {
        List<user> users = friendService.searchNew(input, page);
        if(users == null) {
            return Result.success(ErrorCode.SEARCH_NEW_ERROR);
        }
        return Result.success(users);
    }
}
