package com.nart.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nart.common.LogA;
import com.nart.pojo.FriendChat;
import com.nart.pojo.GroupChat;
import com.nart.service.ChatService;
import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private ChatService chatService;


    @GetMapping("history/{type}/{chatId}")
    public Result showHistory(@PathVariable String type,
                              @PathVariable String chatId) {
        if(type.equals("friend")) {
            List<FriendChat> friendChats = chatService.showFriendHistory(chatId, new Page());
            if(friendChats == null) {
                return Result.fail(ErrorCode.SHOW_FRIEND_CHAT_HISTORY);
            }
            return Result.success(friendChats);
        } else {
            List<GroupChat> groupChats = chatService.showGroupHistory(chatId, new Page());
            if(groupChats == null) {
                return Result.fail(ErrorCode.SHOW_GROUP_CHAT_HISTORY);
            }
            return Result.success(groupChats);
        }
    }

    @PostMapping("send")
    public Result sendMsg(@RequestBody MessageVo msgInfo) {
        if(msgInfo.getType().equals("friend")) {
            FriendChat friendChat = new FriendChat();
            friendChat.setReceiverId(msgInfo.getChatId());
            friendChat.setSenderId(msgInfo.getSenderId());
            friendChat.setMsg(msgInfo.getMsg());
            friendChat.setType(msgInfo.getMsgType());
            friendChat.setDate(new Date().getTime());
            boolean b = chatService.sendFriendMsg(friendChat);
            if(b) {
                return Result.success(null);
            }
            return Result.fail(ErrorCode.SEND_FRIEND_CHAT_HISTORY);
        } else {
            GroupChat groupChat = new GroupChat();
            groupChat.setGroupId(msgInfo.getChatId());
            groupChat.setSenderId(msgInfo.getSenderId());
            groupChat.setMsg(msgInfo.getMsg());
            groupChat.setType(msgInfo.getMsgType());
            groupChat.setDate(new Date().getTime());
            boolean b = chatService.sendGroupMsg(groupChat);
            if(b) {
                return Result.success(null);
            }
            return Result.fail(ErrorCode.SEND_GROUP_CHAT_HISTORY);
        }
    }

}
