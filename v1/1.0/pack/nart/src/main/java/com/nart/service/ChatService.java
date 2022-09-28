package com.nart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nart.pojo.FriendChat;
import com.nart.pojo.GroupChat;
import com.nart.vo.MessageVo;

import java.util.List;

public interface ChatService {

    boolean sendFriendMsg(FriendChat friendChat);

    boolean sendGroupMsg(GroupChat groupChat);

    List<FriendChat> recivicefriendMsg(String reciviceId, IPage page);

    List<GroupChat> recivicegroupMsg(String reciviceId, IPage page);

    List<MessageVo> showFriendHistory(String Id, IPage page);

    List<MessageVo> showGroupHistory(String gId, IPage page);

    boolean leaveRoom (String roomId, Boolean isF);
}
