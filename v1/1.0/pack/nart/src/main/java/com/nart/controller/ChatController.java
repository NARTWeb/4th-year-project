package com.nart.controller;

import com.nart.common.LogA;
import com.nart.pojo.friendChat;
import com.nart.pojo.groupChat;
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
            List<friendChat> friendChats = chatService.showFriendHistory(chatId);
            if(friendChats == null) {
                return Result.fail(ErrorCode.SHOW_FRIEND_CHAT_HISTORY);
            }
            return Result.success(friendChats);
        } else {
            List<groupChat> groupChats = chatService.showGroupHistory(chatId);
            if(groupChats == null) {
                return Result.fail(ErrorCode.SHOW_GROUP_CHAT_HISTORY);
            }
            return Result.success(groupChats);
        }
    }

    @PostMapping("send")
    public Result sendMsg(@RequestBody MessageVo msgInfo) {
        if(msgInfo.getType().equals("friend")) {
            friendChat friendChat = new friendChat();
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
            groupChat groupChat = new groupChat();
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
