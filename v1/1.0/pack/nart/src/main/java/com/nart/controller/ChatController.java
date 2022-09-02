package com.nart.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
            List<FriendChat> friendChats = chatService.showFriendHistory(chatId,null);
            if(friendChats == null) {
                return Result.fail(ErrorCode.SHOW_FRIEND_CHAT_HISTORY);
            }
            return Result.success(friendChats);
        } else {
            List<GroupChat> groupChats = chatService.showGroupHistory(chatId,null);
            if(groupChats == null) {
                return Result.fail(ErrorCode.SHOW_GROUP_CHAT_HISTORY);
            }
            return Result.success(groupChats);
        }
    }

    @PostMapping("send")
    public Result sendMsg(@RequestBody MessageVo msgInfo) {
        if(msgInfo.getType().equals("friend")) {
            FriendChat FriendChat = new FriendChat();
            FriendChat.setReceiverId(msgInfo.getChatId());
            FriendChat.setSenderId(msgInfo.getSenderId());
            FriendChat.setMsg(msgInfo.getMsg());
            FriendChat.setType(msgInfo.getMsgType());
            FriendChat.setDate(new Date().getTime());
            boolean b = chatService.sendFriendMsg(FriendChat);
            if(b) {
                return Result.success(null);
            }
            return Result.fail(ErrorCode.SEND_FRIEND_CHAT_HISTORY);
        } else {
            GroupChat GroupChat = new GroupChat();
            GroupChat.setGroupId(msgInfo.getChatId());
            GroupChat.setSenderId(msgInfo.getSenderId());
            GroupChat.setMsg(msgInfo.getMsg());
            GroupChat.setType(msgInfo.getMsgType());
            GroupChat.setDate(new Date().getTime());
            boolean b = chatService.sendGroupMsg(GroupChat);
            if(b) {
                return Result.success(null);
            }
            return Result.fail(ErrorCode.SEND_GROUP_CHAT_HISTORY);
        }
    }

}
