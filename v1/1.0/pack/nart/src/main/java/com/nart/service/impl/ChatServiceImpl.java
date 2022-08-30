package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nart.dao.friendChatDao;

import com.nart.dao.groupChatDao;

import com.nart.pojo.friendChat;
import com.nart.pojo.groupChat;
import com.nart.pojo.message;
import com.nart.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private friendChatDao FriendChatDao;

    @Autowired
    private groupChatDao GroupChatDao;
    private Object message;


    @Override
    public boolean sendFriendMsg(friendChat friendChat) {
        int insert = FriendChatDao.insert(friendChat);
        return insert>0;
    }

    @Override
    public boolean sendGroupMsg(groupChat groupChat) {
        int insert = GroupChatDao.insert(groupChat);
        return insert>0;
    }

    @Override
    public List<friendChat> recivicefriendMsg(String reciviceId) {

        LambdaQueryWrapper<friendChat> lqw = new LambdaQueryWrapper<friendChat>();

        lqw.eq(friendChat::getReceiverId, reciviceId);
        List<friendChat> message = FriendChatDao.selectList(lqw);

        return message;
    }

    @Override
    public List<groupChat> recivicegroupMsg(String reciviceId) {
        LambdaQueryWrapper<groupChat> lqw = new LambdaQueryWrapper<groupChat>();
        lqw.eq(groupChat::getGroupId, reciviceId);
        List<groupChat> message = GroupChatDao.selectList(lqw);
        return message;
    }

    @Override
    public List<Object> showHistory(String chatId,int frg) {
        if(frg==0){
            LambdaQueryWrapper<friendChat> lqw = new LambdaQueryWrapper<friendChat>();
            lqw.eq(friendChat::getSenderId, chatId);
            List<Object> message = Collections.singletonList(FriendChatDao.selectList(lqw));
            return message;
        }else {
            LambdaQueryWrapper<groupChat> lqw = new LambdaQueryWrapper<groupChat>();
            lqw.eq(groupChat::getGroupId, chatId);
            List<Object> message = Collections.singletonList(GroupChatDao.selectList(lqw));
            return message;
        }
    }


}
