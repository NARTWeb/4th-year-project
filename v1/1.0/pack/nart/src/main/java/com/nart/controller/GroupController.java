package com.nart.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.nart.pojo.groupInvite;
import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.vo.GroupVo;
import com.nart.vo.PageVo;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: GroupController
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/31 14:07
 */
@RestController
@RequestMapping("group")
public class GroupController {
    @GetMapping("memberList/{groupId}")
    public Result showGroupMemberList(@PathVariable("groupId") String gid) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PutMapping("changeInfo")
    public Result changeGroupInfo(@RequestBody GroupVo gInfo) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping("list")
    public Result showGroupList(@RequestBody PageVo page) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @DeleteMapping("del/{groupId}")
    public Result leaveGroup(@PathVariable("groupId") String gid) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PutMapping("state/{groupId}/{state}")
    public Result changeGroupState(@PathVariable("groupId") String gid,
                                   @PathVariable("state") Integer state) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping("inviteList")
    public Result showInviteList(@RequestBody PageVo page) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PostMapping("send")
    public Result sendInvite(@RequestBody groupInvite inviteInfo) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PutMapping("resp/{inviteId}/{agree}")
    public Result respGroupInvite(@PathVariable("inviteId") String inviteId,
                                  @PathVariable("agree") Boolean agree) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PostMapping("create")
    public Result createGroup(@RequestBody String gName) {
        return Result.fail(ErrorCode.UNDEFINED);
    }
}
