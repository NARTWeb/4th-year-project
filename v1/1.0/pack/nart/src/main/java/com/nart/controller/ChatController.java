package com.nart.controller;

import com.nart.common.LogA;
import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.vo.MessageVo;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: ChatController
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/31 15:16
 */
@RestController
@RequestMapping("chat")
@LogA
public class ChatController {


    @GetMapping("history/{type}/{chatId}")
    public Result showHistory(@PathVariable String type,
                              @PathVariable String chatId) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PostMapping("send")
    public Result sendMsg(@RequestBody MessageVo msgInfo) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

}
