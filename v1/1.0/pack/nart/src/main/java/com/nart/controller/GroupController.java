package com.nart.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.common.LogA;
import com.nart.pojo.Group;
import com.nart.pojo.GroupInvite;
import com.nart.pojo.User;
import com.nart.pojo.UserGroup;
import com.nart.service.GroupService;
import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.util.UserThreadLocal;
import com.nart.vo.GroupVo;
import com.nart.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@LogA
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("memberList/{groupId}")
    public Result showGroupMemberList(@PathVariable("groupId") String gid) {
        List<User> Users = groupService.showGroupMebList(gid, new Page());
        if(Users == null) {
            Result.fail(ErrorCode.SHOW_GROUP_MEMBER_ERROR);
        }
        return Result.success(Users);
    }

    @PutMapping("changeInfo")
    public Result changeGroupInfo(@RequestBody GroupVo gInfo) {
        Group group = new Group();
        group.setId(gInfo.getGroupId());
        group.setGroupName(gInfo.getGroupName());
        group.setAvatar(gInfo.getGroupAvatar());
        group.setNotice(gInfo.getNotice());
        boolean b = groupService.changeGroupInfo(group);
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.CHANGE_GROUP_INFO_ERROR);
    }

    @GetMapping("list")
    public Result showGroupList(@RequestBody PageVo page) {
        List<Group> Groups = groupService.showGroupList(page.toIPage(UserGroup.class));
        if(Groups == null) {
            Result.fail(ErrorCode.SHOW_GROUP_LIST_ERROR);
        }
        return Result.success(Groups);
    }

    @DeleteMapping("del/{groupId}")
    public Result leaveGroup(@PathVariable("groupId") String gid) {
        boolean b = groupService.leaveGroup(gid, UserThreadLocal.get().getId());
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.LEAVE_GROUP_ERROR);
    }

    @PutMapping("state/{groupId}/{state}")
    public Result changeGroupState(@PathVariable("groupId") String gid,
                                   @PathVariable("state") Integer state) {
        boolean b = groupService.changeGroupState(gid, UserThreadLocal.get().getId(), state);
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.CHANGE_GROUP_STATE_ERROR);
    }

    @GetMapping("inviteList")
    public Result showInviteList(@RequestBody PageVo page) {
        List<GroupInvite> GroupInvites = groupService.showInviteList(page.toIPage(GroupInvite.class));
        if(GroupInvites == null) {
            Result.fail(ErrorCode.SHOW_GROUP_INVITE_LIST_ERROR);
        }
        return Result.success(GroupInvites);
    }

    @PostMapping("send")
    public Result sendInvite(@RequestBody GroupInvite inviteInfo) {
        boolean b = groupService.sendInvite(inviteInfo);
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.SEND_GROUP_INVITE_ERROR);
    }

    @PutMapping("resp/{inviteId}/{agree}")
    public Result respGroupInvite(@PathVariable("inviteId") String inviteId,
                                  @PathVariable("agree") Boolean agree) {
        boolean b = groupService.respGroupInvite(inviteId, agree);
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.RESP_GROUP_INVITE_ERROR);
    }

    @PostMapping("create")
    public Result createGroup(@RequestBody String gName) {
        boolean b = groupService.createGroup(gName, UserThreadLocal.get().getId());
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.CREATE_GROUP_ERROR);
    }
}
