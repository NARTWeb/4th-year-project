package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.friendChat;
import com.nart.pojo.groupChat;
import com.nart.pojo.message;

import java.util.List;

public interface ChatService {

    boolean sendFriendMsg(friendChat friendChat);

    boolean sendGroupMsg(groupChat groupChat);

    List<friendChat> recivicefriendMsg(String reciviceId,IPage page);

    List<groupChat> recivicegroupMsg(String reciviceId,IPage page);

    List<friendChat> showFriendHistory(String Id, IPage page);

    List<groupChat> showGroupHistory(String gId,IPage page);
}
