package com.nart.service;

import com.nart.pojo.friendChat;
import com.nart.pojo.groupChat;
import com.nart.pojo.message;

import java.util.List;

public interface ChatService {

    boolean sendFriendMsg(friendChat friendChat);

    boolean sendGroupMsg(groupChat groupChat);



    List<friendChat> recivicefriendMsg(String reciviceId);

    List<groupChat> recivicegroupMsg(String reciviceId);

    List<Object> showHistory(String chatId,int frg);
}
