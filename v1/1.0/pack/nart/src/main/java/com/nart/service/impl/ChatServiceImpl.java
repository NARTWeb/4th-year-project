package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.friendChatDao;

import com.nart.dao.groupChatDao;

import com.nart.pojo.*;
import com.nart.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private friendChatDao FriendChatDao;

    @Autowired
    private groupChatDao GroupChatDao;

    @Autowired
    private com.nart.dao.userDao userDao;



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
    public List<friendChat> recivicefriendMsg(String reciviceId,IPage page) {

        LambdaQueryWrapper<friendChat> lqw = new LambdaQueryWrapper<friendChat>();

        lqw.eq(friendChat::getReceiverId, reciviceId);
        IPage iPage = FriendChatDao.selectPage(page, lqw);
        List<friendChat> records = iPage.getRecords();
        return records;
    }

    @Override
    public List<groupChat> recivicegroupMsg(String reciviceId,IPage page) {
        LambdaQueryWrapper<groupChat> lqw = new LambdaQueryWrapper<groupChat>();
        lqw.eq(groupChat::getGroupId, reciviceId);
        IPage iPage = GroupChatDao.selectPage(page, lqw);
        List<groupChat> records = iPage.getRecords();
        return records;
    }

    @Override
    public List<friendChat> showFriendHistory(String Id,IPage page) {
        LambdaQueryWrapper<friendChat> lqw = new LambdaQueryWrapper<friendChat>();
        lqw.eq(friendChat::getSenderId, Id).or().eq(friendChat::getReceiverId, Id);
        lqw.orderBy(true,false,friendChat::getDate);

        IPage iPage = FriendChatDao.selectPage(page, lqw);
        List<friendChat> records = iPage.getRecords();
        return records;
    }

    @Override
    public List<groupChat> showGroupHistory(String gId, IPage page) {

        LambdaQueryWrapper<groupChat> lqw = new LambdaQueryWrapper<groupChat>();
        lqw.eq(groupChat::getGroupId, gId);
        lqw.orderBy(true,false,groupChat::getLevel,groupChat::getDate);

        IPage iPage = GroupChatDao.selectPage(page, lqw);
        List<groupChat> records = iPage.getRecords();
        return records;

    }


}
