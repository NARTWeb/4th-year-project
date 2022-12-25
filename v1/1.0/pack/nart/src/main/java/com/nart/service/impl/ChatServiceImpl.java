package com.nart.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.dao.FriendDao;
import com.nart.dao.GroupDao;
import com.nart.dao.UserDao;
import com.nart.dao.UserGroupDao;
import com.nart.pojo.*;
import com.nart.service.ChatService;
import com.nart.service.DataCounterService;
import com.nart.util.RedisUtil;
import com.nart.util.UserThreadLocal;
import com.nart.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private DataCounterService dataCounterService;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private  FriendDao friendDao;

    @Autowired
    private UserGroupDao userGroupDao;

    @Override
    public boolean sendFriendMsg(FriendChat friendChat) {

//        检查权限
//        String id = UserThreadLocal.get().getId();
//        Object o = redisUtil.get(id);

        int insert = FriendChatDao.insert(friendChat);
        dataCounterService.updateMessageAmount(true);
        return insert>0;
    }



    @Override
    public boolean sendGroupMsg(GroupChat groupChat) {
        String groupId = groupChat.getGroupId();

        Group group = groupDao.selectById(groupId);
        int userLevel = group.getUserLevel();
        groupChat.setLevel(userLevel);
        int insert = GroupChatDao.insert(groupChat);
        dataCounterService.updateMessageAmount(true);
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
    public List<MessageVo> showFriendHistory(String Id, IPage page) {
        LambdaQueryWrapper<FriendChat> lqw = new LambdaQueryWrapper<FriendChat>();
        lqw.eq(FriendChat::getSenderId, Id).or().eq(FriendChat::getReceiverId, Id);
        lqw.orderBy(true,false, FriendChat::getDate);

        IPage iPage = FriendChatDao.selectPage(page, lqw);
        List<FriendChat> records = iPage.getRecords();
        System.out.println(records);
        MessageVo messageVo = new MessageVo();
        List<MessageVo> messageVos = new ArrayList<>();

        for (FriendChat record : records) {
            MessageVo ftransfer = messageVo.Ftransfer(record);

            User user = userDao.selectById(record.getSenderId());
//            System.out.println(user);
            if(user!=null){
                ftransfer.setSenderName(user.getName());
                ftransfer.setSenderAvatar(user.getAvatar());
            }

            messageVos.add(ftransfer);
        }
        return messageVos;
    }

    @Override
    public List<MessageVo> showGroupHistory(String gId, IPage page) {

        LambdaQueryWrapper<GroupChat> lqw = new LambdaQueryWrapper<GroupChat>();
        lqw.eq(GroupChat::getGroupId, gId);
        //按照最近和level最大来输出聊天记录
        lqw.orderBy(true,false, GroupChat::getLevel, GroupChat::getDate);

        IPage iPage = GroupChatDao.selectPage(page, lqw);
        List<GroupChat> records = iPage.getRecords();

        MessageVo messageVo = new MessageVo();
        List<MessageVo> messageVos = new ArrayList<>();

        for (GroupChat record : records) {
            MessageVo ftransfer = messageVo.Gtransfer(record);

            User user = userDao.selectById(record.getSenderId());
            if (user != null) {
                ftransfer.setSenderName(user.getName());
                ftransfer.setSenderAvatar(user.getAvatar());
            }


            messageVos.add(ftransfer);
        }

        return messageVos;

    }

    @Override
    public boolean leaveRoom(String roomId, Boolean isF) {
        if (isF){
            LambdaQueryWrapper<Friend> lqw = new LambdaQueryWrapper<Friend>();
            lqw.eq(Friend::getFid, roomId);
            lqw.eq(Friend::getUid, UserThreadLocal.get().getId());

            Friend friend = friendDao.selectOne(lqw);
            long timeStamp = getTimeStamp();
            friend.setLeaveTime(timeStamp);
            int i = friendDao.updateById(friend);
            return i>0;
        }else {
            LambdaQueryWrapper<UserGroup> lqw = new LambdaQueryWrapper<UserGroup>();
            lqw.eq(UserGroup::getGid, roomId);
            lqw.eq(UserGroup::getUid, UserThreadLocal.get().getId());
            UserGroup userGroup = userGroupDao.selectOne(lqw);

            long t = getTimeStamp();

//            System.out.println(t);
//            int statusid1 = Math.toIntExact(t);
//            System.out.println("int"+statusid1);
//
//            String timeStamp = String.valueOf(statusid1);
//            String wei = "000";
//            String he = timeStamp+wei;
//            System.out.println(he);

            userGroup.setUserLevelTime(String.valueOf(t));
            int i = userGroupDao.updateById(userGroup);
            return i>0;
        }

    }

    public long getTimeStamp() {
        String currentDate = getCurrentDate();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sf.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long s = Long.parseLong(String.valueOf(date.getTime()));
        return s;
    }

    public static String getCurrentDate() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(d);
    }


}
