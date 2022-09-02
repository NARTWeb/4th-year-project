package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.nart.dao.GroupDao;
import com.nart.dao.UserDao;
import com.nart.pojo.*;
import com.nart.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private com.nart.dao.FriendChatDao FriendChatDao;

    @Autowired
    private com.nart.dao.GroupChatDao GroupChatDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;



    @Override
    public boolean sendFriendMsg(FriendChat friendChat) {
        int insert = FriendChatDao.insert(friendChat);
        return insert>0;
    }



    @Override
    public boolean sendGroupMsg(GroupChat groupChat) {
        String groupId = groupChat.getGroupId();

        Group group = groupDao.selectById(groupId);
        int userLevel = group.getUserLevel();
        groupChat.setLevel(userLevel);
        int insert = GroupChatDao.insert(groupChat);
        return insert>0;
    }

    @Override
    public List<FriendChat> recivicefriendMsg(String reciviceId, IPage page) {

        LambdaQueryWrapper<FriendChat> lqw = new LambdaQueryWrapper<FriendChat>();
        lqw.eq(FriendChat::getReceiverId, reciviceId);
        IPage iPage = FriendChatDao.selectPage(page, lqw);
        List<FriendChat> records = iPage.getRecords();
        return records;
    }

    @Override
    public List<GroupChat> recivicegroupMsg(String reciviceId, IPage page) {
        LambdaQueryWrapper<GroupChat> lqw = new LambdaQueryWrapper<GroupChat>();
        lqw.eq(GroupChat::getGroupId, reciviceId);
        IPage iPage = GroupChatDao.selectPage(page, lqw);
        List<GroupChat> records = iPage.getRecords();
        return records;
    }

    @Override
    public List<FriendChat> showFriendHistory(String Id, IPage page) {
        LambdaQueryWrapper<FriendChat> lqw = new LambdaQueryWrapper<FriendChat>();
        lqw.eq(FriendChat::getSenderId, Id).or().eq(FriendChat::getReceiverId, Id);
        lqw.orderBy(true,false, FriendChat::getDate);

        IPage iPage = FriendChatDao.selectPage(page, lqw);
        List<FriendChat> records = iPage.getRecords();
        return records;
    }

    @Override
    public List<GroupChat> showGroupHistory(String gId, IPage page) {

        LambdaQueryWrapper<GroupChat> lqw = new LambdaQueryWrapper<GroupChat>();
        lqw.eq(GroupChat::getGroupId, gId);
        //按照最近和level最大来输出聊天记录
        lqw.orderBy(true,false, GroupChat::getLevel, GroupChat::getDate);

        IPage iPage = GroupChatDao.selectPage(page, lqw);
        List<GroupChat> records = iPage.getRecords();
        return records;

    }


}
