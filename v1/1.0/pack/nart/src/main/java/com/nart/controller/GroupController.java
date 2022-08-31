package com.nart.controller;

import com.nart.util.ErrorCode;
import com.nart.util.Result;
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
    public Result changeGroupInfo(){
        return Result.fail(ErrorCode.UNDEFINED);
    }
}
