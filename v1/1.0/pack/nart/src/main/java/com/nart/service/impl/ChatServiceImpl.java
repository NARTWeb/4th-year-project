package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nart.dao.friendChatDao;

import com.nart.dao.groupChatDao;

import com.nart.dao.userDao;
import com.nart.pojo.friendChat;
import com.nart.pojo.groupChat;
import com.nart.pojo.message;
import com.nart.pojo.user;
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
    @Autowired
    private userDao userDao;



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
    public List<friendChat> showFriendHistory(String Id) {
        LambdaQueryWrapper<friendChat> lqw = new LambdaQueryWrapper<friendChat>();
        lqw.eq(friendChat::getSenderId, Id).or().eq(friendChat::getReceiverId, Id);
        List<friendChat> friendChats = FriendChatDao.selectList(lqw);

        return friendChats;
    }

    @Override
    public List<groupChat> showGroupHistory(String gId) {

        LambdaQueryWrapper<groupChat> lqw = new LambdaQueryWrapper<groupChat>();
        lqw.eq(groupChat::getGroupId, gId);
        List<groupChat> groupChats = GroupChatDao.selectList(lqw);
        return groupChats;

    }


}
